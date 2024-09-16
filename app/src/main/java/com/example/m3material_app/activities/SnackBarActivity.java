package com.example.m3material_app.activities;

import static android.icu.text.DisplayContext.LENGTH_SHORT;
import static com.example.m3material_app.R.string.cancel;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m3material_app.R;
import com.example.m3material_app.databinding.ActivitySnackbarBinding;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarActivity extends AppCompatActivity {
        ActivitySnackbarBinding binding;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySnackbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        layout = binding.main;
        Button btnSnackBar = binding.btnSnackBar;
        Snackbar snackbar;
        snackbar=Snackbar.make(layout,"Warning",LENGTH_LONG);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SnackBarActivity.this,"Retrying",Toast.LENGTH_SHORT).show();
            }

        });
        snackbar.setAnchorView(btnSnackBar);

        btnSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.show();
            }
        });

    }
}