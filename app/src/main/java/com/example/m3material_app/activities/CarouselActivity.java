package com.example.m3material_app.activities;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3material_app.R;
import com.example.m3material_app.adapters.ImageAdapter;
import com.example.m3material_app.models.CarouselModel;

import java.util.ArrayList;

public class CarouselActivity extends AppCompatActivity {
        ArrayList<CarouselModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carousel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.recycle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        arrayList.add(new CarouselModel(R.drawable.img1));
        arrayList.add(new CarouselModel(R.drawable.img3));
        arrayList.add(new CarouselModel(R.drawable.img5));
        arrayList.add(new CarouselModel(R.drawable.img4));
        arrayList.add(new CarouselModel(R.drawable.img5));

        ImageAdapter imageAdapter= new ImageAdapter(arrayList,this);
        recyclerView.setAdapter(imageAdapter);


    }

}