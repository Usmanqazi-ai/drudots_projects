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
import com.example.express.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    String minNum = "10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.forgotPasswordPhoneNum.getText().toString().isEmpty()) {

                    SnackBar mSnackBar = new SnackBar(ForgotPasswordActivity.this);
                    mSnackBar.showSnackBar("please enter phone number", "Require!");
                }
                if (binding.forgotPasswordPhoneNum.getText().length() != Integer.parseInt(minNum)) {

                    SnackBar mSnackBar = new SnackBar(ForgotPasswordActivity.this);
                    mSnackBar.showSnackBar("please enter valid phone number", "Require!");
                } else {
                    Intent intentForgotPassword = new Intent(ForgotPasswordActivity.this, VerificationCodeActivity.class);
                    intentForgotPassword.putExtra("phoneNumber", binding.forgotPasswordPhoneNum.getText().toString().trim());
                    intentForgotPassword.putExtra("countryCode", binding.getCountryCode.getText());
                    intentForgotPassword.setAction("ForgotPassword_Activity");
                    startActivity(intentForgotPassword);
                }

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
                        binding.forgotPasswordPhoneNum.setHint(hint);
                        InputFilter[] filterArray = new InputFilter[1];
                        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
                        binding.forgotPasswordPhoneNum.setFilters(filterArray);
                        binding.forgotPasswordPhoneNum.setText("");

                    }
                });

        binding.pakFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, CountryListActivity.class);
                activityResultLauncher.launch(intent);

            }
        });
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
        binding.forgotPasswordPhoneNum.setFilters(filterArray);
    }
}