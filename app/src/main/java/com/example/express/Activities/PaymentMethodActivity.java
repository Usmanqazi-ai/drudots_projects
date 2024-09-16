package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.express.Classes.SnackBar;
import com.example.express.databinding.ActivityPaymentMethodBinding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class PaymentMethodActivity extends AppCompatActivity {
    ActivityPaymentMethodBinding binding;
    String seatNum;
    String passengerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentMethodBinding.inflate(getLayoutInflater());
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

        binding.BtnProccedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.CardHolderName.getText().toString().isEmpty()) {
                    SnackBar snackBar = new SnackBar(PaymentMethodActivity.this);
                    snackBar.setAnchor(binding.someText, "please enter Name", "Required");
                } else if (binding.txtValidDate.getText().toString().isEmpty()) {
                    SnackBar snackBar = new SnackBar(PaymentMethodActivity.this);
                    snackBar.setAnchor(binding.someText, "please enter card valid date", "Required");

                } else if (binding.cvvNum.getText().toString().isEmpty()) {
                    SnackBar snackBar = new SnackBar(PaymentMethodActivity.this);
                    snackBar.setAnchor(binding.someText, "please enter CVV number", "Required");
                } else {

                    Intent intent = new Intent(PaymentMethodActivity.this, PaymentSuccessfulActivity.class);
                    intent.putExtra("seatNum", seatNum);
                    intent.putExtra("passengerName", passengerName);
                    startActivity(intent);

                }
            }
        });
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());


        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("select Date");
        materialDateBuilder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        binding.txtValidDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "set Date");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(selection);
                SimpleDateFormat format = new SimpleDateFormat("MM/YY");
                String formattedDate = format.format(calendar.getTime());
                binding.txtValidDate.setText(formattedDate);

            }
        });
        binding.backBtnPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}