package com.example.express.Activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.express.Adapters.FAQAdapter;
import com.example.express.Classes.FAQModel;
import com.example.express.databinding.ActivityFaqactivityBinding;

import java.util.ArrayList;

public class FAQActivity extends AppCompatActivity {
    ActivityFaqactivityBinding binding;
    ArrayList<FAQModel> faqModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFaqactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        faqModelArrayList.add(new FAQModel("what is online booking and do I use it?","Online booking is a convenient way of buying tickets over the internet."));
        faqModelArrayList.add(new FAQModel("How do I know if my purchase was successful?","A few minutes after your purchase you will receive confirmation SMS & Email that will contain summary of you order along with E-ticket."));
        faqModelArrayList.add(new FAQModel("Can Customers choose their own seat?","Yes, customer can choose his/her seat according to their will."));
        faqModelArrayList.add(new FAQModel("What ticket types are available for online purchase?Yes, customer can choose his/her seat according to their will.","The following ticket types are currently available to purchase online"));
        faqModelArrayList.add(new FAQModel("What if password is been forgotten?","If you forget your password, then no need to worry. Just click on Forgot Password and provide the email address with which you get registered with Drive. An Email will be sent to you and you can reset your password through that email."));

        binding.rvFaq.setLayoutManager(new LinearLayoutManager(this));
        FAQAdapter faqAdapter = new FAQAdapter(faqModelArrayList, this);
        binding.rvFaq.setAdapter(faqAdapter);

        binding.backBtnFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}