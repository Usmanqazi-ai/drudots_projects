package com.example.express.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.express.R;
import com.example.express.databinding.ActivityTicketsShowBinding;

public class TicketsShowActivity extends AppCompatActivity {

    ActivityTicketsShowBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketsShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        Intent intent = getIntent();
        String seatNum = intent.getStringExtra("seatNum");
        String passengerName = intent.getStringExtra("passengerName");
        binding.passengerNameTicket.setText(passengerName);
        binding.setNumMyTicket.setText(seatNum);

        binding.menuMyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(TicketsShowActivity.this,binding.menuMyTicket);
            }
        });


        binding.backButtonMyTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
    }


    public void showPopupMenu(Context context, View anchor) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_menu, null);


        PopupWindow popupWindow = new PopupWindow(popupView, 400, 120, true);
        popupWindow.showAsDropDown(anchor);

        TextView textView = popupView.findViewById(R.id.popupItem);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketsShowActivity.this, HelpActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });



    }
}