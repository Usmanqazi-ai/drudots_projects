package com.example.m3material_app.activities;

import static android.app.PendingIntent.getActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.example.m3material_app.R;
import com.example.m3material_app.databinding.ActivityDialogsBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogsActivity extends AppCompatActivity {
    ActivityDialogsBinding binding;
    Context context = DialogsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDialogsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonDialog = binding.btnDialog;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(context.getResources().getString(R.string.alert_title));
        dialogBuilder.setMessage(context.getResources().getString(R.string.start_massage));
        dialogBuilder.setNeutralButton(context.getResources().getString(R.string.cancel), (dialog, which) -> {
            // Respond to neutral button press
        });
        dialogBuilder.setNegativeButton(context.getResources().getString(R.string.decline), (dialog, which) -> {

        });
        dialogBuilder.setPositiveButton(context.getResources().getString(R.string.accept), (DialogInterface dialog, int which) -> {

        });
        AlertDialog alertDialog = dialogBuilder.create();

        buttonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });


    }

}