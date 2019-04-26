package com.ndifreke.developers.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.ndifreke.developers.model.GithubUsers;
import com.ndifreke.developers.R;
import java.util.List;
import com.ndifreke.developers.GithubUsersFactory;
import android.widget.RelativeLayout;
import java.util.Map;
import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;
import java.util.LinkedList;

public class DeveloperListAdapter extends RecyclerView.Adapter<DeveloperViewHolder> {
    private static final String Tag = "Adapter SCROLL POSITION";
    private List<GithubUsers> developerList;

    public DeveloperListAdapter() {
        Map<String, GithubUsers> developers = GithubUsersFactory.createDevelopers(
                "{items: [" +
                        "{ login:\"Njuibe\", avatar_url:\"\"}" +
                        "]" +
                        "}"
        ).getDevelopers();
        developerList = new LinkedList<>(developers.values());
    }

    @Override
    public DeveloperViewHolder onCreateViewHolder(ViewGroup parent, int index) {
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