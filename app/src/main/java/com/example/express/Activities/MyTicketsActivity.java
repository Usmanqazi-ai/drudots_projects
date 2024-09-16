package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.express.Adapters.MyTicketsAdapter;
import com.example.express.Classes.MyTicketsModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.databinding.ActivityMyTicketsBinding;

import java.util.ArrayList;

public class MyTicketsActivity extends AppCompatActivity {

    ArrayList<MyTicketsModel> myTicketsModelArrayList = new ArrayList<>();

    ActivityMyTicketsBinding binding;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MyTicketsActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyTicketsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        Intent intent = getIntent();
       String seatNum = intent.getStringExtra("seatNum");
       String passengerName = intent.getStringExtra("passengerName");

        binding.backButtonMyTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyTicketsActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


        binding.recyclerViewMyTicket.setLayoutManager(new LinearLayoutManager(this));
        MyTicketsAdapter myTicketsAdapter = new MyTicketsAdapter(myTicketsModelArrayList, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent1 = new Intent(MyTicketsActivity.this ,TicketsShowActivity.class);
                intent1.putExtra("seatNum",seatNum);
                intent1.putExtra("passengerName",passengerName);
                startActivity(intent1);
            }
        });
        binding.recyclerViewMyTicket.setAdapter(myTicketsAdapter);
        myTicketsModelArrayList.add(new MyTicketsModel(seatNum, passengerName));
        myTicketsModelArrayList.add(new MyTicketsModel(seatNum, passengerName));
    }
}