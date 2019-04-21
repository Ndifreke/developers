package com.ndifreke.developers.dialog;

import com.ndifreke.developers.Developer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ndifreke.developers.R;


public class ShareDialog extends DialogFragment {

    public ShareDialog() {
        super();
    }

    public ShareDialog(Developer profile) {
        this();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.share_overlay);
        LinearLayout dialogLayout = (LinearLayout) getView();
        TextView gihubName = dialogLayout.findViewById(R.id.share_github_name);
        TextView githubURL = dialogLayout.findViewById(R.id.share_github_url);

        OnClickListener onClose = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        };

        builder.setPositiveButton("Positive", onClose );
        return builder.create();
    };
};
