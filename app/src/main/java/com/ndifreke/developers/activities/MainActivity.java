package com.ndifreke.developers.activities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;
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