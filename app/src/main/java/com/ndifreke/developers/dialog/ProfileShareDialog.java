package com.ndifreke.developers.dialog;

import com.ndifreke.developers.model.GithubUser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ndifreke.developers.R;


public class ProfileShareDialog extends AppCompatDialogFragment {

    public AlertDialog.Builder dialog;
    public GithubUser githubUser;


    public ProfileShareDialog(){
    }

    public ProfileShareDialog(GithubUser githubUser){
        super();
        this.githubUser = githubUser;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        dialog = new AlertDialog.Builder(getActivity());
        View dialogLayout = getActivity().getLayoutInflater().inflate(R.layout.profile_share_dialog, null);
        TextView githubName = dialogLayout.findViewById(R.id.share_github_name);
        TextView githubURL = dialogLayout.findViewById(R.id.share_github_url);
        final TextView closeDialog = dialogLayout.findViewById(R.id.closeDialog);
        githubName.setText("@".concat(this.githubUser.getName()));
        githubURL.setText(this.githubUser.getProfileURL());
        dialog.setView(dialogLayout);
        return dialog.create();
    };
};