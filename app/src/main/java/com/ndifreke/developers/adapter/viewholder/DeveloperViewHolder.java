package com.ndifreke.developers.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ndifreke.developers.R;

public class DeveloperViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout viewHolder;
    public TextView gihubName;
    public ImageView avatar;

    public DeveloperViewHolder(RelativeLayout view){
        super(view);
        viewHolder = view;
        gihubName = view.findViewById(R.id.githubName);
        avatar = view.findViewById(R.id.imageAvater);
    }

}