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
import com.ndifreke.developers.R;
import com.ndifreke.developers.activities.model.MainViewModel;
import com.ndifreke.developers.adapter.GithubUserAdapter;

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
        MainViewModel model = ViewModelProviders.of(this).get(MainViewModel.class);
        model.getGithubAdapter().observe(this, new Observer<Adapter>() {
            @Override
            public void onChanged(@Nullable Adapter adapter) {
                initRecycler(adapter);
            }
        });
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