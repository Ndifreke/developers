package com.ndifreke.developers.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ndifreke.developers.R;

import android.util.Log;
import android.widget.RelativeLayout;

import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;


public class DeveloperListAdapter extends RecyclerView.Adapter<DeveloperViewHolder> {
    private static final String Tag = "Adapter SCROLL POSITION";

    private int[] resources = null;

    public DeveloperListAdapter() {
        resources = this.stubResources();
    }

    private int[] stubResources() {
        int[] resources = new int[17];
        for (int i = 0; i < 17; i++) {
            resources[i] = R.drawable.ic_small_profile;
        }
        return resources;
    }

    @Override
    public DeveloperViewHolder onCreateViewHolder(ViewGroup parent, int index) {
        Log.i(Tag, parent.getClass().toString());
        RelativeLayout developerHolder = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developer_list_viewholder, parent, false);
        return new DeveloperViewHolder(developerHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperViewHolder viewHolder, int scrollPos) {
        viewHolder.avatar.setImageResource(resources[scrollPos]);
        Log.i("menn", String.valueOf(resources[scrollPos]));
        viewHolder.gihubName.setText("Developer ".concat(String.valueOf(scrollPos)));

    }

    @Override
    public int getItemCount() {
        return this.resources.length;
    }
}