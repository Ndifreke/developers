package com.ndifreke.developers.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ndifreke.developers.util.GithubUserFactory;
import com.ndifreke.developers.R;
import com.ndifreke.developers.dialog.ProfileShareDialog;
import com.ndifreke.developers.model.GithubUser;

public class DetailActivity extends AppCompatActivity {
    private GithubUser githubUser;
    private ProfileShareDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.detail_activity);
        this.initToolbar();
        Intent intent = getIntent();

        String username
                = intent.getStringExtra(GithubUser.USER);

        this.githubUser = GithubUserFactory.fetchAUser(username);
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
        TextView name = findViewById(R.id.detailGithubName);
        name.setText(githubUser.getName());
        TextView profileLink = findViewById(R.id.detailGithubLink);
        ImageView avatar = findViewById(R.id.detailImage);
        avatar.setImageBitmap(githubUser.getImageResource());
        profileLink.setText(githubUser.getProfileURL());
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
                        githubUser.getName()));
        startActivity(intent);
    }

    public void dismisDialog(View view) {
        this.dialog.dismiss();
    }
}
