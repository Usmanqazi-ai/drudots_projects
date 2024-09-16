package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.express.databinding.ActivityPaymentSuccessfulBinding;

public class PaymentSuccessfulActivity extends AppCompatActivity {
    ActivityPaymentSuccessfulBinding binding;
    private String seatNum;
    private String passengerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentSuccessfulBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        Intent intent = getIntent();
        seatNum = intent.getStringExtra("seatNum");
        passengerName = intent.getStringExtra("passengerName");
        binding.seatNum.setText(seatNum);
        binding.passengerName.setText(passengerName);
        binding.backBtnPaymentSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnPaymentDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentSuccessfulActivity.this, MyTicketsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("seatNum", seatNum);
                intent.putExtra("passengerName", passengerName);
                startActivity(intent);
                finish();
            }
        });
    }
}