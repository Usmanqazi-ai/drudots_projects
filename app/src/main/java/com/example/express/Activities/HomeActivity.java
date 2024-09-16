package com.example.express.Activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.express.Fragments.HomeFragment;
import com.example.express.Fragments.MoreFragment;
import com.example.express.Fragments.NotificationFragment;
import com.example.express.R;
import com.example.express.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        loadFragment(new HomeFragment());
        setSelected(binding.navHome);

        binding.navHome.setOnClickListener(v -> {
            loadFragment(new HomeFragment());
            setSelected(binding.navHome);
        });

        binding.navNotification.setOnClickListener(v -> {
            loadFragment(new NotificationFragment());
            setSelected(binding.navNotification);
        });

        binding.navMore.setOnClickListener(v -> {
            loadFragment(new MoreFragment());
            setSelected(binding.navMore);
        });
    }

    private void setSelected(LinearLayout selectedItem) {
        binding.titleNavHome.setVisibility(View.GONE);
        binding.titleNavNotification.setVisibility(View.GONE);
        binding.titleNavMore.setVisibility(View.GONE);
        binding.navHome.setBackgroundResource(R.drawable.background_transparent);
        binding.navNotification.setBackgroundResource(R.drawable.background_transparent);
        binding.navMore.setBackgroundResource(R.drawable.background_transparent);
        binding.titleNavHome.setSelected(false);
        binding.titleNavNotification.setSelected(false);
        binding.titleNavMore.setSelected(false);


        if (selectedItem == binding.navHome) {
            binding.titleNavHome.setVisibility(View.VISIBLE);
            binding.navHome.setBackgroundResource(R.drawable.navigation_background);
        } else if (selectedItem == binding.navNotification) {
            binding.titleNavNotification.setVisibility(View.VISIBLE);
            binding.navNotification.setBackgroundResource(R.drawable.navigation_background);
        } else if (selectedItem == binding.navMore) {
            binding.titleNavMore.setVisibility(View.VISIBLE);
            binding.navMore.setBackgroundResource(R.drawable.navigation_background);
        }

        selectedItem.setSelected(true);


    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homeFrameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }




}

