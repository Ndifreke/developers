package com.ndifreke.developers.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.ndifreke.developers.R;
import com.ndifreke.developers.dialog.ProfileShareDialog;
import com.ndifreke.developers.model.GithubUser;

public class DeveloperProfileActivity extends AppCompatActivity {
    private GithubUser githubUser;
    private ProfileShareDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.profile_activity);
        this.initToolbar();
        Intent intent = getIntent();
        Parcelable parcelable
                = intent.getParcelableExtra("com.ndifreke.developers.model.GithubUser");
        this.githubUser = (GithubUser) parcelable;
        this.setViewContent(githubUser);
    }

    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getResources().getString(R.string.PROFILE_TITLE));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setViewContent(GithubUser githubUser) {
        TextView name = findViewById(R.id.githubName);
        name.setText(githubUser.getName());
        TextView profileLink = findViewById(R.id.githubLink);
        profileLink.setText("\\@".concat(githubUser.getAvatarURL()));
    }

    public void shareProfile(View view) {
        dialog = new ProfileShareDialog(this.githubUser);
        dialog.show(getSupportFragmentManager(), null);
    }

    public void dismisDialog(View view){
        this.dialog.dismiss();
    }
}
