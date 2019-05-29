package com.ndifreke.developers.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ndifreke.developers.R;
import com.ndifreke.developers.features.githubusers.GithubUser;
import com.ndifreke.developers.activities.DetailActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DeveloperViewHolder extends RecyclerView.ViewHolder{

    private RelativeLayout viewHolder;
    private TextView gihubNameView;
    private ImageView avatarView;
    private Context context;
    private GithubUser githubUser;

    public DeveloperViewHolder(RelativeLayout view, final Context context) {
        super(view);
        viewHolder = view;
        gihubNameView = view.findViewById(R.id.githubName);
        avatarView = view.findViewById(R.id.imageAvater);
        this.context = context;
    }

//    @BindView(R.id.viewHolder) View v;
//    public void showProfile(View view){
//        Intent intent = DetailActivity.startIntent(context);
//        intent.putExtra(GithubUser.ID, this.githubUser.getProfileURL());
//        context.startActivity(intent);
//    }

    public void setDeveloper(final GithubUser githubUser){
        this.setName(githubUser.getUsername());
        this.setImage(githubUser.getImageResource());
        this.githubUser = githubUser;
//
//
        viewHolder.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = DetailActivity.startIntent(context);
                        intent.putExtra(GithubUser.ID, githubUser.getProfileURL());
                        context.startActivity(intent);
                    }
                });
    }

    private void setName(CharSequence charSequence){
        this.gihubNameView.setText(charSequence);
    }

    private void setImage(Bitmap image){
        if(image != null)
            this.avatarView.setImageBitmap(image);
    }

}