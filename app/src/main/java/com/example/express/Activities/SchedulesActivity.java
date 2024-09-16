package com.example.express.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.express.Classes.SnackBar;
import com.example.express.R;
import com.example.express.databinding.ActivitySchedulesBinding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class SchedulesActivity extends AppCompatActivity {


    ActivitySchedulesBinding binding;
    String[] departureCities = {"Departure City", "Lahore", "Karachi", "Lahore", "Faisalabad", "Rawalpindi", "Gujranwala", "Peshawar", "Multan", "Hyderabad",
            "Islamabad"};
    String[] arrivalCities = {"Arrival City", "Lahore", "Karachi", "Lahore", "Faisalabad", "Rawalpindi", "Gujranwala", "Peshawar", "Multan", "Hyderabad",
            "Islamabad"};
    boolean isItemSelected = false;
    boolean isItemSelected2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySchedulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        ArrayAdapter<String> departure_ad = new ArrayAdapter<String>(this, R.layout.spinner_list_cities, departureCities) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);

                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        ArrayAdapter<String> arrival_ad = new ArrayAdapter<String>(this, R.layout.spinner_list_cities, arrivalCities) {

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };



        departure_ad.setDropDownViewResource(R.layout.spinner_list_cities);
        arrival_ad.setDropDownViewResource(R.layout.spinner_list_cities);
        binding.departureCitySpinner.setAdapter(departure_ad);
        binding.departureCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                if (value.equals(departureCities[0])) {
                    ((TextView) view).setTextColor(Color.GRAY);

                }
                if (position == 0) {
                    isItemSelected = true;
                } else {
                    isItemSelected = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        binding.arrivalCitySpinner.setAdapter(arrival_ad);
        binding.arrivalCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                if (value.equals(arrivalCities[0])) {

                    ((TextView) view).setTextColor(Color.GRAY);

                }
                if (position == 0) {
                    isItemSelected2 = true;
                } else {
                    isItemSelected2 = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });



        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(DateValidatorPointForward.now());

        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("select Date");
        materialDateBuilder.setCalendarConstraints(constraintsBuilder.build());
        MaterialDatePicker materialDatePicker = materialDateBuilder.build();
//        materialDateBuilder.setTheme(R.style.ThemeOverlay.App.DatePicker);


        binding.txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "set Date");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(selection);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
                String formattedDate = format.format(calendar.getTime());
                binding.txtDate.setText(formattedDate);

            }
        });


        binding.backBtnSchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.findBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemSelected) {
                    SnackBar snackBar = new SnackBar(SchedulesActivity.this);
                    snackBar.setAnchor(binding.txtForSnack, "please select departure city", "Required");
                } else if (isItemSelected2) {
                    SnackBar snackBar = new SnackBar(SchedulesActivity.this);
                    snackBar.setAnchor(binding.txtForSnack, "please select arrival city", "Required");
                } else if (binding.txtDate.getText().toString().isEmpty()) {
                    SnackBar snackBar = new SnackBar(SchedulesActivity.this);
                    snackBar.setAnchor(binding.txtForSnack, "please select date", "Required");
                } else {
                    Intent intent = new Intent(SchedulesActivity.this, BusResultActivity.class);
                    startActivity(intent);


                }

            }
        });


    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    }
}