package com.example.express.Classes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.express.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBar {
    Activity activity;
    public SnackBar(Activity activity) {
        this.activity = activity;
    }
    @SuppressLint("RestrictedApi")
    public void showSnackBar(String message, String warning) {
        View rootview = activity.findViewById(R.id.main);
        Snackbar snackBar = Snackbar.make(rootview, "some text", 1000);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackBar.getView();
        View customView = LayoutInflater.from(activity).inflate(R.layout.custom_snack_bar, null);

        TextView snackBarText1 = customView.findViewById(R.id.txtSnackBar);
        snackBarText1.setText(warning);

        TextView snackBarText2 = customView.findViewById(R.id.txtSnackBar2);
        snackBarText2.setText(message);

        snackBar.getView().setBackgroundColor(Color.TRANSPARENT);
        snackbarLayout.addView(customView);


        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) snackbarLayout.getLayoutParams();
        params.gravity = android.view.Gravity.TOP;
        snackbarLayout.setLayoutParams(params);


        snackBar.show();


    }

    public void setAnchor(TextView id, String message, String warning){
        View rootview = activity.findViewById(R.id.main);
        Snackbar snackBar = Snackbar.make(rootview, "some text", 1000);
        @SuppressLint("RestrictedApi") Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackBar.getView();
        View customView = LayoutInflater.from(activity).inflate(R.layout.custom_snack_bar, null);

        TextView snackBarText1 = customView.findViewById(R.id.txtSnackBar);
        snackBarText1.setText(warning);

        TextView snackBarText2 = customView.findViewById(R.id.txtSnackBar2);
        snackBarText2.setText(message);

        snackBar.getView().setBackgroundColor(Color.TRANSPARENT);
        snackbarLayout.addView(customView);
        snackBar.setAnchorView(id);
        snackBar.show();

    }
}
