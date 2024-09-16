package com.example.m3material_app.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import com.example.m3material_app.R;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_date_picker);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            Button materialDatePickerBtn = findViewById(R.id.materialDatePicker);
            MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
            materialDateBuilder.setTitleText("select Date");
//            materialDateBuilder.setTheme(R.style.ShapeAppearance_App_MediumComponent);
            MaterialDatePicker materialDatePicker= materialDateBuilder.build();

            materialDatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             materialDatePicker.show(getSupportFragmentManager(),"Material Date Picker");

            }
        });
            Button materialDateRangePickerBtn = findViewById(R.id.materialDateRangePicker);
            MaterialDatePicker.Builder<Pair<Long,Long>> materialDateRangeBuilder = MaterialDatePicker.Builder.dateRangePicker();
            materialDateRangeBuilder.setTitleText("Select Date");
            MaterialDatePicker<Pair<Long, Long>> materialDateRangePicker = materialDateRangeBuilder.build();
            materialDateRangePickerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                materialDateRangePicker.show(getSupportFragmentManager(),"Date Range Picker");

            }
        });

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        Button btnTimePicker = findViewById(R.id.btnTimePicker);
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               timePickerFragment.show(getSupportFragmentManager(),"timepicker");
            }
        });

        DatePickerFragment datePickerFragment= new DatePickerFragment();
        Button button = findViewById(R.id.btnDatePicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               datePickerFragment.show(getSupportFragmentManager(),"date picker");

            }
        });
    }
     public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar calendar= Calendar.getInstance();
            int date = calendar.get(Calendar.DATE);

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(),this,hour,minute, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        }

    }
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{


        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),this,year,month,day);
        }

        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {

        }
    }
}