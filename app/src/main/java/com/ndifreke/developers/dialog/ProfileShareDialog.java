package com.ndifreke.developers.dialog;

import com.ndifreke.developers.model.GithubUser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ndifreke.developers.R;


public class ProfileShareDialog extends DialogFragment {

    public static AlertDialog.Builder builder;

    public ProfileShareDialog(){
    }

    public static ProfileShareDialog newInstance(GithubUser githubUser, Context context){
        return new ProfileShareDialog();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.profile_share_dialog, null);
        builder.setView(view);
        LinearLayout dialogLayout = (LinearLayout) view;
        TextView gihubName = dialogLayout.findViewById(R.id.share_github_name);
        TextView githubURL = dialogLayout.findViewById(R.id.share_github_url);

        OnClickListener onClose = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        };

        builder.setPositiveButton("Positive", onClose );
        Log.i("dialog", builder.toString());
        return builder.create();
    };
};