package com.ndifreke.developers;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import static com.ndifreke.developers.R.*;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ndifreke.developers.adapter.DeveloperListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_developer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Nairobi Developers");
        }
        this.initRecycler();
    }

    public void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.developerListRecycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(new DeveloperListAdapter());
        recyclerView.setLayoutManager(layoutManager);
    }

    public void showProfile(View view){

    }
}
