package com.ndifreke.developers.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;

import com.ndifreke.developers.R;
import com.ndifreke.developers.adapter.GithubUserAdapter;
import com.ndifreke.developers.api.GithubAPIs;

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
        fetchDevelopersAsync();
    }

    public void initRecycler(RecyclerView.Adapter adapter) {
        RecyclerView recyclerView = findViewById(R.id.developerListRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void fetchDevelopersAsync(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connMgr != null) {
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if(networkInfo.isConnected()){
                new GithubAPIs(this).execute();
            }
        }
    }



}