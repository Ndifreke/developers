package com.ndifreke.developers.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ndifreke.developers.R;
import com.ndifreke.developers.dialog.ProfileShareDialog;
import com.ndifreke.developers.model.githubusers.GithubCacheHelper;
import com.ndifreke.developers.model.githubusers.GithubUser;
import com.ndifreke.developers.model.githubusers.GithubUserListener;
import android.support.v7.graphics.Palette;

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

        this.githubUser = GithubCacheHelper.cachedGithubUsers.get(profileID);
        githubUser.setListener(new GithubUserListener() {
            @Override
            public void notifyUpdate(GithubUser user) {
                setViewContent(user);
            }
        });

        githubUser.requestUpate();
        this.setViewContent(githubUser);
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

    public void setViewContent(GithubUser githubUser) {
        TextView name = findViewById(R.id.detailGithubName);
        name.setText(githubUser.getUsername());
        TextView profileLink = findViewById(R.id.detailGithubLink);
        ImageView avatar = findViewById(R.id.detailImage);
        avatar.setImageBitmap(githubUser.getImageResource());
        toolBarColorFromPalette(githubUser.getImageResource()); //
        profileLink.setText(githubUser.getProfileURL());
        TextView organizationView = findViewById(R.id.githubNameOrganization);
        organizationView.setText(githubUser.getCompany());
    }

    public void openShareDialog(View view) {
        dialog = new ProfileShareDialog(this.githubUser);
        dialog.show(getSupportFragmentManager(), null);
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

    public void toolBarColorFromPalette(Bitmap profileImage){
      Palette.from(profileImage).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int rgb = palette.getDominantSwatch().getRgb();
                actionBar.setBackgroundDrawable(new ColorDrawable(rgb));
            }
        });

    }
}
