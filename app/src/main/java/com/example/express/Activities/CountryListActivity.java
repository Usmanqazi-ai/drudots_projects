package com.example.express.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.express.Adapters.CountryAdapter;
import com.example.express.Classes.CountryModel;
import com.example.express.R;
import com.example.express.databinding.ActivityCounteryListBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CountryListActivity extends AppCompatActivity {
    ActivityCounteryListBinding binding;

    ArrayList<CountryModel> countryModelArrayList = new ArrayList<>();
    CountryAdapter countryAdapter;
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityCounteryListBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        binding.backBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerCountryDialog);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryModelArrayList = loadCountiesFromJson();
        countryAdapter = new CountryAdapter(countryModelArrayList);
        recyclerView.setAdapter(countryAdapter);


        countryAdapter.setListenerOnClick(new CountryAdapter.ListenerOnClick() {
            @Override
            public void onClickListener(CountryModel countryModel) {
                Intent intent = new Intent();
                intent.putExtra("Code", countryModel.getDial_code());
                intent.putExtra("Flag", countryModel.getFlag());
                intent.putExtra("hint", countryModel.getFormat());
                intent.putExtra("minNum", countryModel.getDigit1());
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    private ArrayList<CountryModel> loadCountiesFromJson() {
        String json = null;
        try {
            InputStream is = getAssets().open("countries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CountryModel>>() {
        }.getType();
        return gson.fromJson(json, listType);


    }


}