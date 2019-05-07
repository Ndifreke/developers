package com.ndifreke.developers.model.githubusers;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GithubUserResponse {

    @SerializedName("items")
    private List<GithubUser> developers ;

    public List<GithubUser> getDevlopers(){
        return developers;
    }



}
