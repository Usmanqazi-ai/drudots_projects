package com.example.express.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.express.Activities.ChatAdminActivity;
import com.example.express.Adapters.ActiveReportsAdapter;
import com.example.express.Classes.ActiveReportsModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.databinding.FragmentActiveReportsBinding;

import java.util.ArrayList;


public class ActiveReportsFragment extends Fragment {
    FragmentActiveReportsBinding binding;
    ArrayList<ActiveReportsModel> activeReportsModelArrayList = new ArrayList<>();
    ActivityResultLauncher<Intent> activityResultLauncher;

    public ActiveReportsFragment(ActivityResultLauncher<Intent> activityResultLauncher) {
        this.activityResultLauncher=activityResultLauncher;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.activeReportList.setLayoutManager(new LinearLayoutManager(getContext()));
        ActiveReportsAdapter activeReportsAdapter = new ActiveReportsAdapter(activeReportsModelArrayList, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity().getApplication(), ChatAdminActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        binding.activeReportList.setAdapter(activeReportsAdapter);
        activeReportsModelArrayList.add(new ActiveReportsModel("Active","#000"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentActiveReportsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

}