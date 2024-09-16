package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.express.Adapters.BusResultAdapter;
import com.example.express.Classes.BusResultModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.databinding.ActivityBusResultBinding;

import java.util.ArrayList;

public class BusResultActivity extends AppCompatActivity {
    ActivityBusResultBinding binding;
    ArrayList<BusResultModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        binding.busResultList.setLayoutManager(new LinearLayoutManager(this));
        BusResultAdapter busResultAdapter = new BusResultAdapter(list, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (position == 0) {
                    Intent intent = new Intent(BusResultActivity.this, SeatSelectionActivity.class);
                    BusResultActivity.this.startActivity(intent);
                } else {
                    Intent intent = new Intent(BusResultActivity.this, SeatSelectionActivity2.class);
                    BusResultActivity.this.startActivity(intent);
                }
            }
        });
        binding.busResultList.setAdapter(busResultAdapter);
        list.add(new BusResultModel("Yutong", "06:30 AM"));
        list.add(new BusResultModel("Daewoo", "07:30 AM"));

        binding.backBtnBusResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}