package com.ndifreke.developers.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ndifreke.developers.model.GithubUser;
import com.ndifreke.developers.R;

import java.util.List;

import com.ndifreke.developers.GithubUsersFactory;

import android.widget.RelativeLayout;

import java.util.Map;

import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;

import java.util.LinkedList;

public class DeveloperListAdapter extends RecyclerView.Adapter<DeveloperViewHolder> {
    private static final String Tag = "Adapter SCROLL POSITION";
    private List<GithubUser> developerList;

    public DeveloperListAdapter() {
        Map<String, GithubUser> developers = GithubUsersFactory.createDevelopers(generateDev())
                .getDevelopers();
        developerList = new LinkedList<>(developers.values());
        Log.i("factoris", developerList.size()+"");
    }

    private static String generateDev() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ items: [ ");
        for (int i = 0; i < 10; i++) {
            sb.append(String.format(" { login :\"%1s\", avatar_url: \"%2s\", url:\"%3s\" },",
                    "Developer ".concat(String.valueOf(i)),
                    "http://github.com/image/".concat(String.valueOf(i)),
                    "http://github.com/user".concat(String.valueOf(i))
            ));
        }
        sb.append("] }");
        return sb.toString();
    }

    @Override
    public DeveloperViewHolder onCreateViewHolder(ViewGroup parent, int index) {
        RelativeLayout developerHolder = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developer_viewholder, parent, false);
        return new DeveloperViewHolder(developerHolder, parent.getContext());
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