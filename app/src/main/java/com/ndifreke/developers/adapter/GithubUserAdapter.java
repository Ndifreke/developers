package com.ndifreke.developers.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ndifreke.developers.model.GithubUser;
import com.ndifreke.developers.R;
import java.util.List;

import android.widget.RelativeLayout;
import java.util.Map;
import com.ndifreke.developers.adapter.viewholder.DeveloperViewHolder;
import com.ndifreke.developers.model.GithubUserListener;

import java.util.LinkedList;

public class GithubUserAdapter extends RecyclerView.Adapter<DeveloperViewHolder> implements GithubUserListener {

    private List<GithubUser> developerList = null;

    public GithubUserAdapter() {
    }

    /**
     * Add A collection of Github users to this Adapter
     * which will be used as dataset in the recycler views
     * @param users Map of GithubUsers
     */
    public void setDataSet(Map<String, GithubUser> users){
        this.createGithubUserList(users);
        for(GithubUser user : this.developerList){
            user.setListener(this);
        }
    }

    private void createGithubUserList(Map<String, GithubUser> users){
        List<GithubUser> userList = new LinkedList<>(users.values());
        this.developerList = userList;
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
//        Log.i("avater", "checking list");
        return developerList == null ? 0 : developerList.size();
    }
}