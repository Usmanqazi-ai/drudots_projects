package com.example.express.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.express.Fragments.ActiveReportsFragment;
import com.example.express.Fragments.CloseReportsFragment;
import com.example.express.R;
import com.example.express.databinding.ActivityReportsBinding;

public class ReportsActivity extends AppCompatActivity {
    ActivityReportsBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_FIRST_USER) {
                        Intent data = result.getData();
                        if(data != null){
                            String txt = data.getStringExtra("KeyValue");
                            loadFragment(new CloseReportsFragment());
                            setSelected(binding.closedBtn);
                        }
                    }
                });


        binding.backBtnMyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadFragment(new ActiveReportsFragment(activityResultLauncher));
        setSelected(binding.activeBtn);

        binding.activeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ActiveReportsFragment(activityResultLauncher));
                setSelected(binding.activeBtn);

            }
        });
        binding.closedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new CloseReportsFragment());
                setSelected(binding.closedBtn);
            }
        });





    }
    private void setSelected(TextView selectedItem) {
//
        binding.activeBtn.setBackgroundResource(R.drawable.background_transparent);
        binding.closedBtn.setBackgroundResource(R.drawable.background_transparent);
//


        if (selectedItem ==binding.activeBtn) {
            binding.activeBtn.setBackgroundResource(R.drawable.bg_active_btn);
        } if (selectedItem ==binding.closedBtn) {
            binding.closedBtn.setBackgroundResource(R.drawable.bg_active_btn);
        }

        selectedItem.setSelected(true);
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.reportFrameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}