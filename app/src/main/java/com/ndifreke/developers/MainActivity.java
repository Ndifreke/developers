package com.ndifreke.developers;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ndifreke.developers.adapter.DeveloperListAdapter;
import com.ndifreke.developers.dialog.ShareDialog;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter avaterImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Kampala Developers");
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.developerListRecycler);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new DeveloperListAdapter());
        DialogFragment df = new ShareDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mInflate = getMenuInflater();
//        mInflate.inflate(R.menu.profile_menu, menu);
        return true;
    }

    public void showProfile(View view){
        Log.i("onclick", view.toString());
    }

    public void onClickBackButton (MenuItem backButton){

    }
}
