package com.ndifreke.developers.activities.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import android.util.Log;

import com.ndifreke.developers.activities.GlobalContext;
import com.ndifreke.developers.adapter.GithubUserAdapter;
import com.ndifreke.developers.api.GithubAPI;
import com.ndifreke.developers.features.githubusers.GithubUser;
import com.ndifreke.developers.features.githubusers.GithubUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Adapter> adapterMutableLiveData;
    private List<GithubUser> devepers;

    public LiveData<Adapter> getGithubAdapter() {
        if (adapterMutableLiveData == null || devepers == null) {
            adapterMutableLiveData = new MutableLiveData<>();
            boolean hasCachedAdapter = GlobalContext.cachedAdapter != null;
            if (hasCachedAdapter) {
                setAdapterFromCache(GlobalContext.cachedAdapter);
            }else {
                initGithubAdapter();
            }
        }
        return adapterMutableLiveData;
    }

    private void setAdapterFromCache(Adapter adapter){
        adapterMutableLiveData.setValue(adapter);
    }

    private void initGithubAdapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubAPI githubUserApiRetrofit = retrofit.create(GithubAPI.class);
        Call<GithubUserResponse> call = githubUserApiRetrofit.getUsers();
        call.enqueue(new Callback<GithubUserResponse>() {

            @Override
            public void onResponse(Call<GithubUserResponse> call, Response<GithubUserResponse> response) {
                devepers = response.body().getDevlopers();
                GithubUserAdapter adapter = new GithubUserAdapter();
                adapter.setDataSet(devepers);
                GlobalContext.cachedAdapter = adapter;
                adapterMutableLiveData.postValue(adapter);
            }

            @Override
            public void onFailure(Call<GithubUserResponse> call, Throwable t) {
                adapterMutableLiveData.postValue(null);
                Log.i("xxx", t.getMessage());
                t.printStackTrace();
            }
        });
    }


}
