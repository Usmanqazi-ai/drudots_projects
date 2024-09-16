package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.express.Adapters.HelpAdapter;
import com.example.express.Classes.HelpModel;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;
import com.example.express.databinding.ActivityHelpBinding;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    ActivityHelpBinding binding;
    ArrayList<HelpModel> helpModelArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        binding.backBtnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.helpRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HelpAdapter helpAdapter = new HelpAdapter(helpModelArrayList, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position==0){
                    Intent intent = new Intent(HelpActivity.this, ReportProblemActivity.class);
                    startActivity(intent);
                }
                if (position==1){
                    Intent intent = new Intent(HelpActivity.this, ReportsActivity.class);
                    startActivity(intent);

                }if (position == 2){
                    Intent intent = new Intent(HelpActivity.this, FAQActivity.class);
                    startActivity(intent);
                }

            }
        });

        binding.helpRecyclerView.setAdapter(helpAdapter);

        helpModelArrayList.add(new HelpModel(R.drawable.ic_report,"Report a Problem"));
        helpModelArrayList.add(new HelpModel(R.drawable.ic_my_report,"My Reports"));
        helpModelArrayList.add(new HelpModel(R.drawable.ic_faq,"FAQ's"));
    }
}