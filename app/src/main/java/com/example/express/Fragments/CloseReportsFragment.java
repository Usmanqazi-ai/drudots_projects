package com.example.express.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.express.Activities.ChatAdminActivity;
import com.example.express.Adapters.ActiveReportsAdapter;
import com.example.express.Classes.ActiveReportsModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.databinding.FragmentCloseReportsBinding;

import java.util.ArrayList;


public class CloseReportsFragment extends Fragment {

        FragmentCloseReportsBinding binding;
    ArrayList<ActiveReportsModel> activeReportsModelArrayList = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.closeReportList.setLayoutManager(new LinearLayoutManager(getContext()));
        ActiveReportsAdapter activeReportsAdapter = new ActiveReportsAdapter(activeReportsModelArrayList, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity().getApplication(), ChatAdminActivity.class);
                startActivity(intent);
            }
        });

        binding.closeReportList.setAdapter(activeReportsAdapter);
        activeReportsModelArrayList.add(new ActiveReportsModel("Close","#000"));
        activeReportsModelArrayList.add(new ActiveReportsModel("Close","#001"));


    }


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    binding= FragmentCloseReportsBinding.inflate(inflater,container,false);
    return binding.getRoot();
}
}