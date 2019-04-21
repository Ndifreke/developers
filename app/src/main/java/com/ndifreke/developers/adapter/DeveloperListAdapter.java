package com.ndifreke.developers.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ndifreke.developers.Developer;
import com.ndifreke.developers.R;

import android.util.Log;
import android.widget.RelativeLayout;

import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;
import com.ndifreke.developers.DeveloperFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DeveloperListAdapter extends RecyclerView.Adapter<DeveloperViewHolder> {
    private static final String Tag = "Adapter SCROLL POSITION";
    private List<Developer> developerList;

    public DeveloperListAdapter() {
        Map<String, Developer> developers = DeveloperFactory.createDevelopers(
                "{items: [" +
                        "{ login:\"Njuibe\", avatar_url:\"\"}" +
                        "]" +
                        "}"
        ).getDevelopers();
        developerList = new LinkedList<>(developers.values());
    }

    @Override
    public DeveloperViewHolder onCreateViewHolder(ViewGroup parent, int index) {
        Log.i(Tag, parent.getClass().toString());
        RelativeLayout developerHolder = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developer_list_viewholder, parent, false);
        return new DeveloperViewHolder(developerHolder);
    }

    @Override
    public void onBindViewHolder(DeveloperViewHolder viewHolder, int scrollPos) {
        viewHolder.setDeveloper(developerList.get(scrollPos));
    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }
}
