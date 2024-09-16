package com.example.express.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.express.Classes.SnackBar;
import com.example.express.databinding.ActivityPassengerDetailBinding;

public class PassengerDetailActivity extends AppCompatActivity {
    ActivityPassengerDetailBinding binding;
    private String minNum = "10";
    private String seatNum;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPassengerDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        seatNum = getIntent().getStringExtra("seatNum");

        binding.backBtnPassengerDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String countryFlag = data.getStringExtra("Flag");
                        String countryCode = data.getStringExtra("Code");
                        String hint = data.getStringExtra("hint");
                        minNum = data.getStringExtra("minNum");
                        binding.getFlag.setText(countryFlag);
                        binding.getCountryCode.setText(countryCode);
                        binding.passengerDetailPhoneNum.setHint(hint);

                        InputFilter[] filterArray = new InputFilter[1];
                        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
                        binding.passengerDetailPhoneNum.setFilters(filterArray);
                        binding.passengerDetailPhoneNum.setText("");


                    }
                });
        binding.pakFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassengerDetailActivity.this, CountryListActivity.class);
                activityResultLauncher.launch(intent);

            }
        });

        binding.passengerDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.passengerDetailName.getText().toString().isEmpty()) {

                    SnackBar mSnackBar = new SnackBar(PassengerDetailActivity.this);
                    mSnackBar.setAnchor(binding.textMainPassenger, "please enter name", "Require!");
                } else if (binding.passengerDetailPhoneNum.getText().length() != Integer.parseInt(minNum)) {

                    SnackBar mSnackBar = new SnackBar(PassengerDetailActivity.this);
                    mSnackBar.setAnchor(binding.textMainPassenger, "please enter valid phone number", "Require!");
                } else {
                    Intent intent = new Intent(PassengerDetailActivity.this, TicketDetailsActivity.class);
                    intent.putExtra("passengerName", binding.passengerDetailName.getText().toString());
                    intent.putExtra("passengerCcode", binding.getCountryCode.getText().toString());
                    intent.putExtra("passengerNum", binding.passengerDetailPhoneNum.getText().toString());
                    intent.putExtra("seatNum", seatNum);
                    startActivity(intent);
                }
            }
        });
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
        binding.passengerDetailPhoneNum.setFilters(filterArray);

    }
}