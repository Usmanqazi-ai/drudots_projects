package com.example.express.Activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.express.Classes.SnackBar;
import com.example.express.R;
import com.example.express.databinding.ActivityReportProblemBinding;

public class ReportProblemActivity extends AppCompatActivity {

    ActivityReportProblemBinding binding;
    boolean isItemSelected ;

        String[] reportCategories = {"Select Category","Difficulty finding tickets for dates.","Errors or glitches during ticket selection.","Payment failures or errors.","Inaccurate seat availability information.",
        "Difficulty contacting customer support.","Error messages or failures during reservation."};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportProblemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        } else {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        binding.backBtnReportProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_list_report_category, reportCategories) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView) view).setTextColor(Color.BLACK);
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                ((TextView) view).setTextColor(Color.WHITE);
                return view;
            }
        };

//       MaterialSpinner spinner = findViewById(R.id.reportCategorySpinner);
//       spinner.setItems(reportCategories);
//       spinner.setTextColor(Color.BLACK);

       binding.reportCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

       binding.reportCategorySpinner.setAdapter(arrayAdapter);

               binding.submitButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if (isItemSelected) {
                           SnackBar snackBar = new SnackBar(ReportProblemActivity.this);
                           snackBar.setAnchor(binding.anchor, "please select category", "Required");
                       } else if (binding.complainEditText.getText().toString().isEmpty()) {
                           SnackBar snackBar = new SnackBar(ReportProblemActivity.this);
                           snackBar.setAnchor(binding.anchor, "please enter complain", "Required");

                       } else {
                           SnackBar snackBar = new SnackBar(ReportProblemActivity.this);
                           snackBar.setAnchor(binding.anchor, "report submit", "Success");
                       }
                   }
               });



    }
}