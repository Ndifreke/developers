package com.ndifreke.developers.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ndifreke.developers.R;
import com.ndifreke.developers.activities.model.MainViewModel;

import util.NetworkUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Nairobi Developers");
        }
        init();
    }

    public void onRefresh(View v) {
        v.setVisibility(View.INVISIBLE);
        init();
        showLoadingProgress();
    }

    public void init() {
        //show loader here
        if (NetworkUtil.isConnected(this)) {
            MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
            model.getGithubAdapter().observe(this, new Observer<Adapter>() {
                @Override
                public void onChanged(@Nullable Adapter adapter) {
                    if(adapter == null){
                        showNetworkErrorMessage();
                    }else{
                        hideNetworkErrorMessage();
                        initRecycler(adapter);
                    }
                }
            });
        }else {
            showNetworkErrorMessage();
        }
    }

    public void hideNetworkErrorMessage() {
        findViewById(R.id.network_error_view).setVisibility(View.GONE);
    }

    public void showNetworkErrorMessage(){
        hideLoadingProgress();
        findViewById(R.id.network_error_view).setVisibility(View.VISIBLE);
        findViewById(R.id.refresh_button).setVisibility(View.VISIBLE);
        findViewById(R.id.network_message).setVisibility(View.VISIBLE);
        findViewById(R.id.network_mask).setVisibility(View.VISIBLE);
    }

    public void showLoadingProgress(){
        findViewById(R.id.load_progress).setVisibility(View.VISIBLE);
    }

    public void hideLoadingProgress(){
        findViewById(R.id.load_progress).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void initRecycler(Adapter adapter) {
        RecyclerView recycler = findViewById(R.id.developerListRecycler);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }

}