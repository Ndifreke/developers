package com.ndifreke.developers.api;

import com.ndifreke.developers.features.githubusers.GithubUserFragment;
import com.ndifreke.developers.features.githubusers.GithubUserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {

    @GET("/search/users?q=type:User+location:Nigeria+language:javascript")
    Call <GithubUserResponse>  getUsers();

    @GET("/users/{username}")
    Call<GithubUserFragment> getProfile( @Path("username") String username);
//
//    @GET("https://api.github.com/users/Davidodari")
//    Call<GithubUserFragment> getProfile();
}
