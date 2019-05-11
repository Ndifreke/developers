package com.ndifreke.developers.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.TextView;
import com.ndifreke.developers.R;
import com.ndifreke.developers.features.githubusers.GithubUser;


public class ProfileShareDialog extends AppCompatDialogFragment {

    public AlertDialog.Builder dialog;
    public GithubUser githubUser;


    public ProfileShareDialog(){
    }

    public void setGithubUser(GithubUser githubUser){
        this.githubUser = githubUser;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        dialog = new AlertDialog.Builder(getActivity());
        View dialogLayout = getActivity().getLayoutInflater().inflate(R.layout.profile_share_dialog, null);
        TextView githubName = dialogLayout.findViewById(R.id.share_github_name);
        TextView githubURL = dialogLayout.findViewById(R.id.share_github_url);
        githubName.setText("@".concat(this.githubUser.getUsername()));
        githubURL.setText(this.githubUser.getProfileURL());
        dialog.setView(dialogLayout);
        return dialog.create();
    }
}