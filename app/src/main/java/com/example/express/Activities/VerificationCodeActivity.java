package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.express.Classes.SnackBar;
import com.example.express.databinding.ActivityVerificationCodeBinding;

import java.util.Objects;

public class VerificationCodeActivity extends AppCompatActivity {
    ActivityVerificationCodeBinding binding;

    EditText[] otpEditTexts = new EditText[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityVerificationCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtOTP1.requestFocus();
        binding.txtOTP1.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (keyboard != null) {
                    keyboard.showSoftInput(binding.txtOTP1, 0);
                }
            }
        },100);

                otpEditTexts[0] = binding.txtOTP1;
                otpEditTexts[1] = binding.txtOTP2;
                otpEditTexts[2] = binding.txtOTP3;
                otpEditTexts[3] = binding.txtOTP4;
                otpEditTexts[4] = binding.txtOTP5;
                otpEditTexts[5] = binding.txtOTP6;


                String getPhoneNumber = getIntent().getStringExtra("phoneNumber");
                String countryCode = getIntent().getStringExtra("countryCode");
                String updatedCountryCode = getIntent().getStringExtra("UserUpdatedCCode");
                String updatedPhoneNumber = getIntent().getStringExtra("UserUpdatedPhoneNumber");
                String action = getIntent().getAction();

                if (Objects.equals(action, "ForgotPassword_Activity")){

                binding.GetCountryCode.setText(countryCode);
                binding.GetPhoneNumber.setText(getPhoneNumber);
                    binding.verifyButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (binding.txtOTP1.getText().toString().isEmpty() || binding.txtOTP2.getText().toString().isEmpty()
                                    || binding.txtOTP3.getText().toString().isEmpty() || binding.txtOTP4.getText().toString().isEmpty()
                                    || binding.txtOTP5.getText().toString().isEmpty() || binding.txtOTP6.getText().toString().isEmpty()) {
                                SnackBar snackBar = new SnackBar(VerificationCodeActivity.this);
                                snackBar.showSnackBar("please enter otp code", "Require!");
                            } else {
                                Intent intent1 = new Intent(VerificationCodeActivity.this, NewPasswordActivity.class);
                                startActivity(intent1);
                            }
                        }
                    });
                }
                else {
                    binding.GetCountryCode.setText(updatedCountryCode);
                    binding.GetPhoneNumber.setText(updatedPhoneNumber);
                    binding.verifyButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (binding.txtOTP1.getText().toString().isEmpty() || binding.txtOTP2.getText().toString().isEmpty()
                                    || binding.txtOTP3.getText().toString().isEmpty() || binding.txtOTP4.getText().toString().isEmpty()
                                    || binding.txtOTP5.getText().toString().isEmpty() || binding.txtOTP6.getText().toString().isEmpty()) {
                                SnackBar snackBar = new SnackBar(VerificationCodeActivity.this);
                                snackBar.showSnackBar("please enter otp code", "Require!");
                            } else {
                                Intent intent1 = new Intent(VerificationCodeActivity.this, HomeActivity.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent1);
                                finish();
                            }
                        }
                    });

                }

                for (int i = 0; i < otpEditTexts.length; i++) {
                    int index = i;
                    otpEditTexts[i].addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() == 1 && index < otpEditTexts.length - 1) {
                                otpEditTexts[index + 1].requestFocus();
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    otpEditTexts[i].setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {

                            if (keyCode == KeyEvent.KEYCODE_DEL) {
                                if (otpEditTexts[index].getText().toString().isEmpty() && index > 0) {
                                    otpEditTexts[index - 1].requestFocus();

                                }
                            }
                            return false;
                        }
                    });
                }


                binding.backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });


                binding.txtResend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SnackBar snackBar = new SnackBar(VerificationCodeActivity.this);
                        snackBar.showSnackBar("code resend successfully", "Success");

                    }
                });

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    View decor = getWindow().getDecorView();
                    decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }




       }
}