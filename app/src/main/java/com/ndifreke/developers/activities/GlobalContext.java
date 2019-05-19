package com.ndifreke.developers.activities;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.ndifreke.developers.features.githubusers.GithubUser;

import java.util.HashMap;
import java.util.Map;

public class GlobalContext extends Application {
    public static Context globalContext;
    public static RecyclerView.Adapter cachedAdapter;
    public static Map<String, GithubUser> cachedUsers = new HashMap<>();

public GlobalContext(){
}
    @Override
    public void onCreate() {
        super.onCreate();
        globalContext = this;
    }
}
