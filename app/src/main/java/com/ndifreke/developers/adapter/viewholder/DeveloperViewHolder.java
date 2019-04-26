package com.ndifreke.developers.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ndifreke.developers.model.GithubUsers;
import com.ndifreke.developers.R;

public class DeveloperViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout viewHolder;
    private TextView gihubNameView;
    private ImageView avatarView;
    private GithubUsers developer = null;

    public DeveloperViewHolder(RelativeLayout view) {
        super(view);
        viewHolder = view;
        gihubNameView = view.findViewById(R.id.githubName);
        avatarView = view.findViewById(R.id.imageAvater);
    }

    public void setDeveloper(GithubUsers developer){
        this.setGihubName(developer.getName());
        this.setImageAvatar(developer.getImageResource());
        this.developer = developer;
    }

    private void setGihubName(CharSequence charSequence){
        this.gihubNameView.setText(charSequence);
    }

    private void setImageAvatar(int resourceId){
        this.avatarView.setImageResource(resourceId);
    }

    /*
     * This method is called by a developer when it state changes
     * either a new image has been received for this developer or his name has
     * changed
     * */
    public void onDeveloperUpdate(GithubUsers developer){
        this.developer.update(this);
    }

}