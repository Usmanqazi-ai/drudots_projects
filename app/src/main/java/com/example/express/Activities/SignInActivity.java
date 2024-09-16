package com.example.express.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.express.Classes.LoginResponse;
import com.example.express.Classes.RetrofitInstance;
import com.example.express.Classes.SnackBar;
import com.example.express.Interfaces.RetrofitRequest;
import com.example.express.R;
import com.example.express.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    private boolean passwordToggle = false;

    private String minNum = "10";
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private RetrofitRequest retrofitRequest;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(SignInActivity.this);


        currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }


        if (android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            if (ContextCompat.checkSelfPermission(SignInActivity.this, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(SignInActivity.this,new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }

        }
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                String token = task.getResult();
                Log.d("my token","on complete"+token);
            }
        });
        creteChannel();


        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        retrofitRequest = retrofit.create(RetrofitRequest.class);



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
                        binding.signInPhoneNum.setHint(hint);
//
//                        InputFilter[] filterArray = new InputFilter[1];
//                        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
//                        binding.signInPhoneNum.setFilters(filterArray);
//                        binding.signInPhoneNum.setText("");


                    }
                });


        binding.loginTxtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.txtRecoverIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent1);
            }
        });

        binding.textLayoutloginPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordToggle) {
                    binding.textLayoutloginPassword.setEndIconDrawable(R.drawable.eye_off);
                    binding.loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordToggle = false;
                } else {
                    binding.textLayoutloginPassword.setEndIconDrawable(R.drawable.eye_open);
                    binding.loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordToggle = true;
                }
                binding.loginPassword.setSelection(binding.loginPassword.length());



            }

        });
        String phone = binding.countryCode.getDefaultCountryCodeWithPlus() + binding.signInPhoneNum.getText().toString().trim();
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.loginPassword.getText().toString().isEmpty()) {
                    SnackBar mSnackBar = new SnackBar(SignInActivity.this);
                    mSnackBar.showSnackBar("please enter password", "Require!");
                }

               else if (binding.signInPhoneNum.getText().toString().isEmpty()) {
                    SnackBar mSnackBar = new SnackBar(SignInActivity.this);
                    mSnackBar.showSnackBar("please enter phone number", "Require!");
                }


                else if (binding.signInPhoneNum.getText().toString().length() < Integer.parseInt(minNum)) {

                    SnackBar snackBar = new SnackBar(SignInActivity.this);
                    snackBar.showSnackBar("please enter valid number", "Require!");
                } else  {
//                    Login(binding.signInPhoneNum.getText().toString().trim(),binding.loginPassword.getText().toString().trim());

                    binding.rlProgressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(binding.signInPhoneNum.getText().toString().trim(), binding.loginPassword.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        binding.rlProgressBar.setVisibility(View.GONE);
                                        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        binding.rlProgressBar.setVisibility(View.GONE);
                                        SnackBar snackBar = new SnackBar(SignInActivity.this);
                                        snackBar.showSnackBar("Wrong credentials", "Required");
                                    }
                                }
                            });
                }


            }
        });
        binding.pakFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, CountryListActivity.class);
                activityResultLauncher.launch(intent);

            }
        });
//
//        InputFilter[] filterArray = new InputFilter[1];
//        filterArray[0] = new InputFilter.LengthFilter(Integer.parseInt(minNum));
//        binding.signInPhoneNum.setFilters(filterArray);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }






    }

    public void MakeNotification(String title,String message) {
        String chnalId = "CHANEL ID";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, chnalId).setSmallIcon(R.drawable.bus_logo_red)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(this, NotificationDisplayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivities(this,0, new Intent[]{intent},PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT>= Build.VERSION_CODES.O ){

            NotificationChannel notificationChannel= notificationManager.getNotificationChannel(chnalId);

            if (notificationChannel==null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                 notificationChannel= new NotificationChannel(chnalId, "some description", importance);
                notificationChannel.setLightColor(R.color.mainColor);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);

            }
        }
        notificationManager.notify(0,builder.build());



    }



  public  void Login(String email,String password){

        binding.rlProgressBar.setVisibility(View.VISIBLE);

       Call<LoginResponse> responseCall = RetrofitInstance.getUserService().login(email, password);

       responseCall.enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               if (response.isSuccessful()){
                   LoginResponse loginResponse =response.body();
                   assert loginResponse != null;
                 boolean status = loginResponse.isStatus();
                 String action = loginResponse.getAction();
//                 String name = loginResponse.getData().getFirst_name();
//                String name1 = name.toUpperCase();
                   binding.rlProgressBar.setVisibility(View.GONE);


                   if (status){
                       Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                       startActivity(intent);
                       finish();
//                       MakeNotification("Welcome",name1 +" welcome to express");
                   }
                   else {
                       SnackBar snackBar = new SnackBar(SignInActivity.this);
                       snackBar.showSnackBar(action, "Require!");

                   }
               }
           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable throwable) {
               binding.rlProgressBar.setVisibility(View.GONE);
               SnackBar snackBar = new SnackBar(SignInActivity.this);
               snackBar.showSnackBar("something went wrong", "Error!");

           }
       });
        }
        private void  creteChannel(){

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                String channelId = "com.example.express";
                String channelName = "myChannel";
                CharSequence charSequence = channelName;
                String description = "My app";
                int importance = NotificationManager.IMPORTANCE_HIGH;

                NotificationChannel notificationChannel = new NotificationChannel(channelId,charSequence,importance);
                notificationChannel.setDescription(description);
                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(notificationChannel);

            }
        }


  }









