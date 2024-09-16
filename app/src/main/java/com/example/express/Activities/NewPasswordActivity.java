package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.express.Classes.SnackBar;
import com.example.express.R;
import com.example.express.databinding.ActivityNewPasswordBinding;

public class NewPasswordActivity extends AppCompatActivity {
    ActivityNewPasswordBinding binding;
    private boolean passwordVisibal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.textLayoutNewPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordVisibal) {
                    binding.textLayoutNewPassword.setEndIconDrawable(R.drawable.eye_off);
                    binding.newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordVisibal = false;
                } else {
                    binding.textLayoutNewPassword.setEndIconDrawable(R.drawable.eye_open);
                    binding.newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordVisibal = true;
                }
                binding.newPassword.setSelection(binding.newPassword.length());
            }
        });
        binding.newPasswordTxtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewPasswordActivity.this, SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        binding.savePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.newPassword.getText().toString().isEmpty()) {

                    SnackBar mSnackBar = new SnackBar(NewPasswordActivity.this);
                    mSnackBar.showSnackBar("please enter name", "Require!");
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}