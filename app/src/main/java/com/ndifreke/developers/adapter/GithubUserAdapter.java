package com.ndifreke.developers.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ndifreke.developers.R;
import java.util.List;

import android.widget.RelativeLayout;

import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;
import com.ndifreke.developers.model.githubusers.GithubCacheHelper;
import com.ndifreke.developers.model.githubusers.GithubUserListener;
import com.ndifreke.developers.model.githubusers.GithubUser;

public class GithubUserAdapter extends RecyclerView.Adapter<DeveloperViewHolder> implements GithubUserListener {

    private List<GithubUser> developerList = null;

    public GithubUserAdapter() {
    }

    /**
     * Add A collection of Github users to this Adapter
     * which will be used as dataset in the recycler views
     * @param users Map of GithubUsers
     */
    public void setDataSet(List<GithubUser> users){
        developerList = users;
        for(GithubUser user : users){
           user.setListener(this);
            GithubCacheHelper.cachedGithubUsers.put(user.getProfileURL(), user);
        }
        Log.i("xxx", users.get(0).getUsername());
    }

    @Override
    public void notifyUpdate(GithubUser user){
        this.notifyItemChanged( this.developerList.indexOf(user), user);
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
        return developerList == null ? 0 : developerList.size();
    }
}