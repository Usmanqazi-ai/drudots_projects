package com.example.m3material_app.activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.m3material_app.R;
import com.example.m3material_app.databinding.ActivityBottomAppBarBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BadgesActivity extends AppCompatActivity {
    ActivityBottomAppBarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_badges);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding=ActivityBottomAppBarBinding.inflate(getLayoutInflater());
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigationView);

        BadgeDrawable badges = binding.bottomNavigationView.getOrCreateBadge(R.id.navhome);
        badges.setVisible(true);


        BadgeDrawable badges_profile = binding.bottomNavigationView.getOrCreateBadge(R.id.navprofile);
        badges_profile.setNumber(15);
        badges_profile.setVisible(true);


        BadgeDrawable badges_massages = binding.bottomNavigationView.getOrCreateBadge(R.id.navmassages);
        badges_massages.setBackgroundColor(Color.RED);
        badges_massages.setBadgeTextColor(Color.WHITE);
        badges_massages.setMaxCharacterCount(4);
        badges_massages.setNumber(1000);
        badges_massages.setVisible(true);
        setContentView(binding.getRoot());

    }
}