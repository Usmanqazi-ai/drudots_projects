package com.example.express.Activities;

import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.express.Classes.SnackBar;
import com.example.express.R;
import com.example.express.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {



    ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        binding.textLayoutOldPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (binding.changeOldPassword.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    binding.textLayoutOldPassword.setEndIconDrawable(R.drawable.eye_open);
                    binding.changeOldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    binding.changeOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    binding.textLayoutOldPassword.setEndIconDrawable(R.drawable.eye_off);


                }
                binding.changeOldPassword.setSelection(binding.changeOldPassword.length());
            }
        });
        binding.textLayoutNewPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.changeNewPassword.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    binding.textLayoutNewPassword.setEndIconDrawable(R.drawable.eye_open);
                    binding.changeNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


                } else {
                    binding.textLayoutNewPassword.setEndIconDrawable(R.drawable.eye_off);
                    binding.changeNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
                binding.changeNewPassword.setSelection(binding.changeNewPassword.length());
            }
        });
        binding.changeConfirmPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.confirmNewPassword.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    binding.changeConfirmPassword.setEndIconDrawable(R.drawable.eye_open);
                    binding.confirmNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    binding.changeConfirmPassword.setEndIconDrawable(R.drawable.eye_off);
                    binding.confirmNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                binding.confirmNewPassword.setSelection(binding.confirmNewPassword.length());
            }
        });
        binding.backButtonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.changeOldPassword.getText().toString().isEmpty()){
                    SnackBar snackBar = new SnackBar(ChangePasswordActivity.this);
                    snackBar.setAnchor(binding.txtChangePassword,"please enter old password","Require");
                } else if (binding.changeNewPassword.getText().toString().isEmpty()) {
                    SnackBar snackBar = new SnackBar(ChangePasswordActivity.this);
                    snackBar.setAnchor(binding.txtChangePassword,"please enter new password","Require");

                } else if (binding.confirmNewPassword.getText().toString().isEmpty()) {
                    SnackBar snackBar = new SnackBar(ChangePasswordActivity.this);
                    snackBar.setAnchor(binding.txtChangePassword,"please enter confirm  password","Require");
                }
                else {
                    SnackBar snackBar = new SnackBar(ChangePasswordActivity.this);
                    snackBar.setAnchor(binding.txtChangePassword,"password changed","Success");
                }
            }
        });
    }
}