package com.example.express.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.express.Activities.PassengerDetailActivity;
import com.example.express.databinding.FragmentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class SeatSelectedBottomSheetFragment extends BottomSheetDialogFragment {
    FragmentBottomSheetBinding binding;
    String seatNum;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), PassengerDetailActivity.class);
                intent.putExtra("seatNum", seatNum);
                startActivity(intent);
                dismiss();
            }
        });
        seatNum = null;
        if (this.getArguments() != null) {
            seatNum = this.getArguments().getString("seatNumber");
        }

        binding.txtSeatNum1.setText(seatNum);
        binding.femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), PassengerDetailActivity.class);
                intent.putExtra("seatNum", seatNum);
                startActivity(intent);

                dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBottomSheetBinding.inflate(inflater, container, false);

        return binding.getRoot();


    }
}