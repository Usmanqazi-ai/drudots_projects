package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.express.databinding.ActivityTicketDetailsBinding;

public class TicketDetailsActivity extends AppCompatActivity {
    ActivityTicketDetailsBinding binding;
    String seatNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketDetailsBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        Intent intent = getIntent();
        String passengerName = intent.getStringExtra("passengerName");
        String passengerNum = intent.getStringExtra("passengerNum");
        String countryCode = intent.getStringExtra("passengerCcode");
        seatNum = intent.getStringExtra("seatNum");
        binding.txtPassengerNumberCountryCode.setText(countryCode);
        binding.txtPassengerNumber.setText(passengerNum);
        binding.txtPassengerName.setText(passengerName);
        binding.passengerSeatNum.setText(seatNum);

        binding.backBtnTicketDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TicketDetailsActivity.this, PaymentMethodActivity.class);
                intent1.putExtra("seatNum", seatNum);
                intent1.putExtra("passengerName", passengerName);
                startActivity(intent1);
            }
        });
    }
}