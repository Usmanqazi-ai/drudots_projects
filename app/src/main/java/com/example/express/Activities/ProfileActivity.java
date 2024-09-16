package com.example.express.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.express.Classes.SnackBar;
import com.example.express.databinding.ActivityProfileBinding;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private String minNum= "10";
    private ActivityResultLauncher<Intent> activityResultLauncher1;
    Bitmap selectedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        binding.backButtonProfile.setOnClickListener(new View.OnClickListener() {
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
                        binding.profilePhoneNumber.setHint(hint);

                        InputFilter[] filterArray = new InputFilter[1];
                        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
                        binding.profilePhoneNumber.setFilters(filterArray);
                        binding.profilePhoneNumber.setText("");


                    }
                });
        binding.pakFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, CountryListActivity.class);
                activityResultLauncher.launch(intent);

            }
        });
        activityResultLauncher1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            Uri selectedImageUri = data.getData();

                            try {
                                selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);


                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }

                             binding.profileImg.setImageBitmap(selectedImageBitmap);


                        }
                    }
                });
        binding.profileImgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();

            }
        });

        binding.profileUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.profileName.getText().toString().isEmpty()) {

                    SnackBar mSnackBar = new SnackBar(ProfileActivity.this);
                    mSnackBar.setAnchor(binding.txtBlank,"please enter name", "Require!");

                } else if (binding.profilePhoneNumber.getText().toString().isEmpty()) {

                    SnackBar mSnackBar = new SnackBar(ProfileActivity.this);
                    mSnackBar.setAnchor(binding.txtBlank,"please enter phone number", "Require!");
                } else if (binding.profilePhoneNumber.getText().length() != Integer.parseInt(minNum)) {

                    SnackBar mSnackBar = new SnackBar(ProfileActivity.this);
                    mSnackBar.setAnchor(binding.txtBlank,"please enter valid phone number", "Require!");
                } else {
                    Intent intent = new Intent(ProfileActivity.this,VerificationCodeActivity.class);
                    intent.putExtra("UserUpdatedCCode",binding.getCountryCode.getText().toString());
                    intent.putExtra("UserUpdatedPhoneNumber",binding.profilePhoneNumber.getText().toString());
                    intent.setAction("Profile_Activity");
                    startActivity(intent);
                }
            }
        });

        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
        binding.profilePhoneNumber.setFilters(filterArray);

    }
    private void imageChooser (){
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        activityResultLauncher1.launch(intent);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         activityResultLauncher1.launch(intent);


    }
}