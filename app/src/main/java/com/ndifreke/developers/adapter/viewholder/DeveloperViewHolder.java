package com.ndifreke.developers.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ndifreke.developers.model.GithubUser;
import com.ndifreke.developers.R;
import com.ndifreke.developers.view.DeveloperProfileActivity;

public class DeveloperViewHolder extends RecyclerView.ViewHolder{

    private RelativeLayout viewHolder;
    private TextView gihubNameView;
    private ImageView avatarView;
    private GithubUser developer = null;

    public DeveloperViewHolder(RelativeLayout view, final Context context) {
        super(view);
        viewHolder = view;
        gihubNameView = view.findViewById(R.id.githubName);
        avatarView = view.findViewById(R.id.imageAvater);
        viewHolder.setOnClickListener(this.onClickDeveloper(context));
    }

    public void setDeveloper(GithubUser developer){
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

    private View.OnClickListener onClickDeveloper(final Context context){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeveloperProfileActivity.class);
                intent.putExtra("com.ndifreke.developers.model.GithubUser", developer);
                context.startActivity(intent);
            }
        };
    }
    /*
     * This method is called by a developer when it state changes
     * either a new image has been received for this developer or his name has
     * changed
     * */
    public void onDeveloperUpdate(GithubUser developer){
        this.developer.update(this);
    }

}