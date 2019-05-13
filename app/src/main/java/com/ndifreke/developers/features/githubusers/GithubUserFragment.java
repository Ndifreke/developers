package com.ndifreke.developers.features.githubusers;

import com.google.gson.annotations.SerializedName;
import com.ndifreke.developers.api.GithubAPI;

import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubUserFragment {

    @SerializedName("company")
    private String company;

    @SerializedName("name")
    private String name;

    private GithubUser mGithubUser;

    private AtomicBoolean fragmentRequestStatus = new AtomicBoolean(false);

    public GithubUserFragment() {
    }

    public void registerGithubUser(GithubUser githubUser) {
        mGithubUser = githubUser;
        requestUpdate();
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    private void fragmentRequestInFlight() {
        this.fragmentRequestStatus.set(true);
    }

    private void fragmentRequestEnd() {
        this.fragmentRequestStatus.set(false);
    }

    private boolean requestinflight() {
        return fragmentRequestStatus.get();
    }

    private void requestUpdate() {

        if (!requestinflight()) {
            fragmentRequestInFlight();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/users/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GithubAPI githubAPI = retrofit.create(GithubAPI.class);
            Call<GithubUserFragment> fragmentCall = githubAPI.getProfile(mGithubUser.getUsername());
            fragmentCall.enqueue(new Callback<GithubUserFragment>() {

                @Override
                public void onResponse(Call<GithubUserFragment> call, Response<GithubUserFragment> response) {
                    if (response.body() != null && mGithubUser != null) {
                        mGithubUser.receiveFragmentUpdate(response.body());
                    }
                    fragmentRequestEnd();
                }

                @Override
                public void onFailure(Call<GithubUserFragment> call, Throwable t) {
                    fragmentRequestEnd();
                }
            });
        }
    }
}
