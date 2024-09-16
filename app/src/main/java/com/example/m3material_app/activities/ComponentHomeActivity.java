package com.example.m3material_app.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3material_app.ListenerOnclick;
import com.example.m3material_app.R;
import com.example.m3material_app.adapters.RecyclerAdapter;
import com.example.m3material_app.models.ComponentModel;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ComponentHomeActivity extends AppCompatActivity implements ListenerOnclick {
    ArrayList<ComponentModel> structureClassArrayList = new ArrayList<>();
    String[] names = {"App Bar", "Bottom App Bar", "Badges", "Buttons", "Cards", "Carousel", "Checkbox", "Chips", "Date Picker", "Time Picker"
            , "Dialogs", "Divider", "Progress Indicators", "Radio Buttons", "Search", "Sliders", "Switch","SnackBar"  , "Tabs", "Text fields","Lists", "Menus"
            , "Navigation", "Sheets"
           , "ToolTips"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycleview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.recycleView1);

        for (int i = 0; i < names.length; i++) {
            structureClassArrayList.add(new ComponentModel(R.drawable.icons8_logo, names[i]));
        }


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerAdapter adapter = new RecyclerAdapter(structureClassArrayList, this);
        recyclerView.setAdapter(adapter);



    }


    @Override
    public void onClickListener(int position) {
        if (names[position].equals("App Bar")) {
            Intent intent = new Intent(ComponentHomeActivity.this, AppBarActivity.class);
            startActivity(intent);
            MaterialCardView cardView = findViewById(R.id.cardView1);
            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    cardView.setChecked(!cardView.isChecked());
                    return false;
                }
            });

        }
        if (names[position].equals("Bottom App Bar")) {
            Intent intent = new Intent(ComponentHomeActivity.this, BottomAppBarActivity.class);
            startActivity(intent);

        }
        if (names[position].equals("Badges")) {
            Intent intent = new Intent(ComponentHomeActivity.this, BadgesActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Buttons")) {
            Intent intent = new Intent(ComponentHomeActivity.this, ButtonsActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Cards")) {
            Intent intent = new Intent(ComponentHomeActivity.this, CardsActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Carousel")) {
            Intent intent = new Intent(ComponentHomeActivity.this, CarouselActivity.class);
            startActivity(intent);
        }

        if (names[position].equals("Checkbox")) {
            Intent intent = new Intent(ComponentHomeActivity.this, CheckboxActivity.class);
            startActivity(intent);
        }

        if (names[position].equals("Chips")) {
            Intent intent = new Intent(ComponentHomeActivity.this, ChipsActivity.class);
            startActivity(intent);
        }

        if (names[position].equals("Date Picker")) {
            Intent intent = new Intent(ComponentHomeActivity.this, DatePickerActivity.class);
            startActivity(intent);
        }

        if (names[position].equals("Time Picker")) {
            Intent intent = new Intent(ComponentHomeActivity.this, TimePickerActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Dialogs")) {
            Intent intent = new Intent(ComponentHomeActivity.this, DialogsActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Divider")) {
            Intent intent = new Intent(ComponentHomeActivity.this, DividerActivity.class);
            startActivity(intent);
        }

        if (names[position].equals("Progress Indicators")) {
            Intent intent = new Intent(ComponentHomeActivity.this, ProgressIndicatorActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Radio Buttons")) {
            Intent intent = new Intent(ComponentHomeActivity.this, RadioButtonActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Search")) {
            Intent intent = new Intent(ComponentHomeActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Sliders")) {
            Intent intent = new Intent(ComponentHomeActivity.this, SliderActivity.class);
            startActivity(intent);
        }

        if (names[position].equals("Switch")) {
            Intent intent = new Intent(ComponentHomeActivity.this, SwitchActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("SnackBar")) {
            Intent intent = new Intent(ComponentHomeActivity.this, SnackBarActivity.class);
            startActivity(intent);
        }
        if (names[position].equals("Tabs")) {
            Intent intent = new Intent(ComponentHomeActivity.this, TabsActivity.class);
            startActivity(intent);
        }
        if (names[position].equals( "Text fields")) {
            Intent intent = new Intent(ComponentHomeActivity.this, TextViewsActivity.class);
            startActivity(intent);
        }
        if (names[position].equals( "Lists")) {
            Intent intent = new Intent(ComponentHomeActivity.this,ListsActivity.class);
            startActivity(intent);
        }

        if (names[position].equals( "Menus")) {
            Intent intent = new Intent(ComponentHomeActivity.this,MenusActivity.class);
            startActivity(intent);
        }
        if (names[position].equals( "Sheets")) {
            Intent intent = new Intent(ComponentHomeActivity.this, SheetsActivity.class);
            startActivity(intent);
        }
        if (names[position].equals( "Navigation")) {
            Intent intent = new Intent(ComponentHomeActivity.this,NavigationActivity.class);
            startActivity(intent);
        }
        if (names[position].equals( "ToolTips")) {
            Intent intent = new Intent(ComponentHomeActivity.this,ToolTipsActivity.class);
            startActivity(intent);
        }
    }
}
