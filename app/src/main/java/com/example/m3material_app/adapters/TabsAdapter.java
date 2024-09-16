package com.example.m3material_app.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.m3material_app.fragments.HomeFragment;
import com.example.m3material_app.fragments.MassagesFragment;
import com.example.m3material_app.fragments.ProfileFragment;

public class TabsAdapter extends FragmentStateAdapter{


    public TabsAdapter( FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new MassagesFragment();
            case 2:
                return new ProfileFragment();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}