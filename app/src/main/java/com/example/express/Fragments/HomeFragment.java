package com.example.express.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.express.Activities.MyTicketsActivity;
import com.example.express.Activities.SchedulesActivity;
import com.example.express.Adapters.ImageSliderAdapter;
import com.example.express.R;
import com.example.express.databinding.FragmentHomeBinding;

import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {


    FragmentHomeBinding binding;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Integer> imageList = Arrays.asList(R.drawable.bus_img1, R.drawable.bus_img1, R.drawable.bus_img1);


        ImageSliderAdapter adapter = new ImageSliderAdapter(getContext(), imageList);
        binding.homeViewPager.setAdapter(adapter);

        binding.indicator.setViewPager(binding.homeViewPager);
        binding.bookSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity().getApplication(), SchedulesActivity.class);
                startActivity(intent);
            }
        });

        binding.homeMyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity().getApplication(), MyTicketsActivity.class);
                startActivity(intent1);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }


}
