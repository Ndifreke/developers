package com.ndifreke.developers.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ndifreke.developers.model.GithubUser;
import com.ndifreke.developers.R;
import com.ndifreke.developers.view.DetailActivity;

public class DeveloperViewHolder extends RecyclerView.ViewHolder{

    private RelativeLayout viewHolder;
    private TextView gihubNameView;
    private ImageView avatarView;
    private Context context;

    public DeveloperViewHolder(RelativeLayout view, final Context context) {
        super(view);
        viewHolder = view;
        gihubNameView = view.findViewById(R.id.githubName);
        avatarView = view.findViewById(R.id.imageAvater);
        this.context = context;
    }

    public void setDeveloper(final GithubUser developer){
        this.setName(developer.getName());
        this.setImage(developer.getImageResource());
        GithubUser developer1 = developer;

        viewHolder.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra(GithubUser.USER, developer.getName());
                        context.startActivity(intent);
                    }
                });
    }

    private void setName(CharSequence charSequence){
        this.gihubNameView.setText(charSequence);
    }

    private void setImage(Bitmap image){
            this.avatarView.setImageBitmap(image);
    }

}