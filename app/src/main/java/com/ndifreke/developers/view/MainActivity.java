package com.ndifreke.developers.view;

import android.support.v4.util.Consumer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;

import com.ndifreke.developers.R;
import com.ndifreke.developers.adapter.GithubUserAdapter;
import com.ndifreke.developers.api.GithubAPI;
import com.ndifreke.developers.model.githubusers.GithubCacheHelper;
import com.ndifreke.developers.model.githubusers.GithubUser;
import com.ndifreke.developers.model.githubusers.GithubUserResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    GithubUserAdapter devListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_developer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Nairobi Developers");
        }
        boolean hasCachedAdapter = GithubCacheHelper.cachedGithubUserAdapter != null;
        if(GithubCacheHelper.cachedGithubUsers.size() > 0 && hasCachedAdapter ){
            initRecycler(GithubCacheHelper.cachedGithubUserAdapter);
        }else {
            fetchDevelopers();
        }
    }

    public void initRecycler(Adapter adapter) {
        RecyclerView recycler = findViewById(R.id.developerListRecycler);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
    }

    private void fetchDevelopers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubAPI githubUserApiRetrofit = retrofit.create(GithubAPI.class);
        Call<GithubUserResponse> call = githubUserApiRetrofit.getUsers();
        call.enqueue(new Callback<GithubUserResponse>() {

            @Override
            public void onResponse(Call<GithubUserResponse> call, Response<GithubUserResponse> response) {
                List<GithubUser> devepers  = response.body().getDevlopers();
                devListAdapter = new GithubUserAdapter();
                devListAdapter.setDataSet(devepers);
                GithubCacheHelper.cachedGithubUserAdapter = devListAdapter;
                initRecycler(devListAdapter);
            }

            @Override
            public void onFailure(Call<GithubUserResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}