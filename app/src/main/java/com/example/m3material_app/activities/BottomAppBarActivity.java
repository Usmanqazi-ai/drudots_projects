package com.example.m3material_app.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.m3material_app.R;
import com.example.m3material_app.databinding.ActivityBottomAppBarBinding;
import com.example.m3material_app.fragments.HomeFragment;
import com.example.m3material_app.fragments.MassagesFragment;
import com.example.m3material_app.fragments.ProfileFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomAppBarActivity extends AppCompatActivity {
            ActivityBottomAppBarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityBottomAppBarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomNavigationView);
        setContentView(binding.getRoot());
        BadgeDrawable badges = binding.bottomNavigationView.getOrCreateBadge(R.id.navmassages);
        badges.setBackgroundColor(Color.RED);
        badges.setBadgeTextColor(Color.WHITE);
        badges.setMaxCharacterCount(4);
        badges.setNumber(1000);
        badges.setVisible(true);
        replaceFragment(new HomeFragment());




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuItemid = item.getItemId();

                if (menuItemid == R.id.navhome){
                    replaceFragment(new HomeFragment());
                    return true;

                }
                if (menuItemid == R.id.navmassages){
                    replaceFragment(new MassagesFragment());
                    badges.setVisible(false );
                    return true;

                } if (menuItemid == R.id.navprofile){
                    replaceFragment(new ProfileFragment());
                    return true;

                }

                return true;
            }

        });
    }
        private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();

        }

}