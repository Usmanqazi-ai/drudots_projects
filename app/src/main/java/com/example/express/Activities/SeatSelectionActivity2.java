package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.express.Adapters.Seat42SelectionAdapter;
import com.example.express.Classes.SeatSelectionModel;
import com.example.express.Classes.SnackBar;
import com.example.express.Interfaces.ItemClickListener;
import com.example.express.R;
import com.example.express.databinding.ActivitySeatSelection2Binding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class SeatSelectionActivity2 extends AppCompatActivity {
    ActivitySeatSelection2Binding binding;
    ArrayList<SeatSelectionModel> seatList = new ArrayList<>();
    private static final int NUM_COLUMNS = 5;
    int position1 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeatSelection2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SeatSelectionActivity2.this, R.style.BottomSheetTheme);
        View view = LayoutInflater.from(SeatSelectionActivity2.this).inflate(R.layout.seat_select_bottom_sheet,findViewById(R.id.bottom_sheet));
        bottomSheetDialog.setContentView(view);


        binding.seatSelectionRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));

//        SeatSelectedBottomSheetFragment bottomSheetFragment = new SeatSelectedBottomSheetFragment();

        Seat42SelectionAdapter seat42SelectionAdapter = new Seat42SelectionAdapter(seatList, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(SeatSelectionActivity2.this, ""+position, Toast.LENGTH_SHORT).show();
//                Bundle bundle = new Bundle();
//                bundle.putString("seatNumber", "" + position);
//                bottomSheetFragment.setArguments(bundle);

                position1= position;
                TextView textSeatNum = view.findViewById(R.id.txtSeatNum);
                textSeatNum.setText(""+position);

                LinearLayout linearMale = view.findViewById(R.id.maleButton);
                linearMale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SeatSelectionActivity2.this, PassengerDetailActivity.class);
                        intent.putExtra("seatNum",""+position);
                        startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });
                LinearLayout linearFemale = view.findViewById(R.id.femaleButton);
                linearFemale.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SeatSelectionActivity2.this, PassengerDetailActivity.class);
                        intent.putExtra("seatNum", ""+position);
                        startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        }, NUM_COLUMNS);
        binding.btnBookSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position1 == -1){
                    SnackBar snackBar = new SnackBar(SeatSelectionActivity2.this);
                    snackBar.setAnchor(binding.txtForSnack,"please select seat","Required");
                }else {

                    bottomSheetDialog.show();

                }



            }
        });

        binding.backBtnSeatSelection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.seatSelectionRecyclerView.setAdapter(seat42SelectionAdapter);
        for (int i = 1; i <= 50; i++) {

            seatList.add(new SeatSelectionModel(("" + i), true, false));
        }


    }



}