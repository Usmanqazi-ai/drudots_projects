package com.example.express.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.express.Classes.SnackBar;
import com.example.express.R;
import com.example.express.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private boolean passwordToggle = false;
   private String minNum= "10";
   FirebaseAuth firebaseAuth;
   FirebaseFirestore firestore;
   FirebaseUser currentUser;
   String userID;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


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
                        binding.signUpPhoneNumber.setHint(hint);

//                        InputFilter[] filterArray = new InputFilter[1];
//                        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
//                        binding.signUpPhoneNumber.setFilters(filterArray);
                        binding.signUpPhoneNumber.setText("");


                    }
                });


        binding.signUpPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordToggle) {
                    binding.signUpPassword.setEndIconDrawable(R.drawable.eye_off);
                    binding.editTextPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordToggle = false;
                } else {
                    binding.editTextPassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    binding.signUpPassword.setEndIconDrawable(R.drawable.eye_open);
                    passwordToggle = true;
                }
                binding.editTextPassword1.setSelection(binding.editTextPassword1.length());
            }
        });

        binding.txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editTextPassword1.getText().toString().isEmpty()) {

                    SnackBar mSnackBar = new SnackBar(SignUpActivity.this);
                    mSnackBar.showSnackBar("please enter password", "Require!");
                } else if (binding.signUpPhoneNumber.getText().toString().isEmpty()) {

                    SnackBar mSnackBar = new SnackBar(SignUpActivity.this);
                    mSnackBar.showSnackBar("please enter phone number", "Require!");
                }
//                else if (binding.signUpPhoneNumber.getText().length() != Integer.parseInt(minNum)) {

//                    SnackBar mSnackBar = new SnackBar(SignUpActivity.this);
//                    mSnackBar.showSnackBar("please enter valid phone number", "Require!");
//                }
               else if (binding.signUpName.getText().toString().isEmpty()) {

                    SnackBar mSnackBar = new SnackBar(SignUpActivity.this);
                    mSnackBar.showSnackBar("please enter name", "Require!");
                } else {

                        binding.rlProgressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(binding.signUpPhoneNumber.getText().toString(), binding.editTextPassword1.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        binding.rlProgressBar.setVisibility(View.GONE);
                                        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                                        DocumentReference documentReference = firestore.collection("users").document(userID);

                                        Map<String, String> user = new HashMap<>();
                                        user.put("name",binding.signUpName.getText().toString().trim());
                                        user.put("email",binding.signUpPhoneNumber.getText().toString().trim());
                                        user.put("password",binding.editTextPassword1.getText().toString().trim());
                                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Log.d("id","Profile is created for :"+userID );

                                            }
                                        });




                                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        binding.rlProgressBar.setVisibility(View.GONE);

                                            SnackBar mSnackBar = new SnackBar(SignUpActivity.this);
                                            mSnackBar.showSnackBar("Already have Account", "Error!");
                                        

                                    }
                                }
                            });

                }
            }
        });
//        InputFilter[] filterArray = new InputFilter[1];
//        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
//        binding.signUpPhoneNumber.setFilters(filterArray);


        binding.pakFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, CountryListActivity.class);
                activityResultLauncher.launch(intent);

            }
        });

    }
}