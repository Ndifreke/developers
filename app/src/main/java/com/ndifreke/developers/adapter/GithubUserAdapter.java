package com.ndifreke.developers.adapter;

import android.os.Handler;
import android.support.v4.util.Consumer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ndifreke.developers.R;

import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import android.widget.RelativeLayout;

import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;
import com.ndifreke.developers.model.githubusers.GithubCacheHelper;
import com.ndifreke.developers.model.githubusers.GithubUserListener;
import com.ndifreke.developers.model.githubusers.GithubUser;

public class GithubUserAdapter extends RecyclerView.Adapter<DeveloperViewHolder> implements GithubUserListener {

    private List<GithubUser> developerList = null;
    private boolean onBind = false;
    private RecyclerView mRecyclerView;

    public GithubUserAdapter() {
    }

    /**
     * Add A collection of Github users to this Adapter
     * which will be used as dataset in the recycler views
     *
     * @param users Map of GithubUsers
     */
    public void setDataSet(List<GithubUser> users) {
        developerList = users;
        for (GithubUser user : users) {
            user.setListener(this);
            GithubCacheHelper.cachedGithubUsers.put(user.getProfileURL(), user);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void notifyUpdate(GithubUser user) {
        if (mRecyclerView != null && !mRecyclerView.isComputingLayout())
            this.notifyItemChanged(this.developerList.indexOf(user));
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
        int count = developerList == null ? 0 : developerList.size();
        return count;
    }
}