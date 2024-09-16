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

import com.example.express.Activities.NotificationDisplayActivity;
import com.example.express.Adapters.NotifactionAdapter;
import com.example.express.Classes.NotifactionModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.databinding.FragmentNotificationBinding;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {
    FragmentNotificationBinding binding;
    ArrayList<NotifactionModel> notifactionModelArrayList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.recyclerViewNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        NotifactionAdapter notifactionAdapter = new NotifactionAdapter(notifactionModelArrayList, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity().getApplication(), NotificationDisplayActivity.class);
                startActivity(intent);
            }
        });
        binding.recyclerViewNotification.setAdapter(notifactionAdapter);
        notifactionModelArrayList.add(new NotifactionModel("Your ticket has been successfully reserved and confirmed.","07:40 AM"));
        notifactionModelArrayList.add(new NotifactionModel("Your ticket has been successfully reserved and confirmed.","07:40 AM"));
        notifactionModelArrayList.add(new NotifactionModel("Your ticket has been successfully reserved and confirmed.","07:40 AM"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }
}