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
import com.ndifreke.developers.model.GithubUser;

public class DeveloperProfileActivity extends AppCompatActivity {
    private GithubUser githubUser;

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
        profileLink.setText(githubUser.getAvatarURL());
    }

    public void shareProfile(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Checkout this Awesome Developer");

        alertDialog.setMessage(Html.fromHtml(
                String.format("<strong>@%1s</strong> <br> <br> %2s",
                        githubUser.getName(), githubUser.getProfileURL()))
        );
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CLOSE",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
