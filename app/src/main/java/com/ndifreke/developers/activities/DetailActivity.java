package com.ndifreke.developers.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ndifreke.developers.R;
import com.ndifreke.developers.dialog.ProfileShareDialog;
import com.ndifreke.developers.features.githubusers.GithubUser;
import com.ndifreke.developers.features.githubusers.GithubUserObserver;
import androidx.palette.graphics.Palette;

public class DetailActivity extends AppCompatActivity {
    private GithubUser githubUser;
    private ProfileShareDialog dialog;
    private boolean isVisible = true;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.detail_activity);
        this.initToolbar();
        Intent intent = getIntent();

        String profileID
                = intent.getStringExtra(GithubUser.ID);
        this.githubUser = GlobalContext.cachedUsers.get(profileID);

        if( this.githubUser != null ) {
            githubUser.setListener(new GithubUserObserver<GithubUser>() {
                @Override
                public void notifyObserver(GithubUser user) {
                    setViewContent(user);
                }
            });
            githubUser.requestUpate();
            this.setViewContent(githubUser);
        }
    }

    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.PROFILE_TITLE));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setViewContent(final GithubUser githubUser) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView name = findViewById(R.id.detailGithubName);
                name.setText(githubUser.getUsername());
                TextView profileLink = findViewById(R.id.detailGithubLink);
                ImageView avatar = findViewById(R.id.detailImageView);
                avatar.setImageBitmap(githubUser.getImageResource());
                toolBarColorFromPalette(githubUser.getImageResource()); //
                profileLink.setText(githubUser.getProfileURL());
                TextView organizationView = findViewById(R.id.githubOrganization);
                System.out.println(githubUser.getCompany());
                organizationView.setText(githubUser.getCompany());
            }
        });
    }

    public void openShareDialog(View view) {
        dialog = new ProfileShareDialog();
        dialog.setGithubUser(this.githubUser);
        dialog.show(getSupportFragmentManager(), null);
    }

    public void setGithubUser(GithubUser user){
        this.githubUser = user;
    }

    public void shareProfile(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "Checkout this Awesome developer");
        intent.putExtra(Intent.EXTRA_TEXT,
                String.format("checkout this awesome developer @%1s",
                        githubUser.getUsername()));
        startActivity(intent);
    }

    public void toggleToolBar(View view){
        if(isVisible){
            actionBar.hide();
            isVisible = false;
        }else{
            actionBar.show();
            isVisible = true;
        }
    }

    public void toolBarColorFromPalette(Bitmap profileImage) {
        if (profileImage != null){
            Palette.from(profileImage).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(@Nullable Palette palette) {
                    int rgb = palette.getDominantSwatch().getRgb();
                    actionBar.setBackgroundDrawable(new ColorDrawable(rgb));
                }
            });

        }
    }
}
