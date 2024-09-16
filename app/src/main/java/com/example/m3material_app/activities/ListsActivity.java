package com.example.m3material_app.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3material_app.R;
import com.example.m3material_app.adapters.ListAdapter;
import com.example.m3material_app.databinding.ActivityListsBinding;
import com.example.m3material_app.models.ListModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListsActivity extends AppCompatActivity {
    ArrayList<ListModel> listArrayList =new ArrayList<>();
       String[] list = new String[]{"Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9",
               "Item10","Item11","Item1","Item13","Item14","Item15","Item16","Item17","Item18","Item19","Item20","Item21"};
    ActivityListsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityListsBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        RecyclerView recycleView1 = binding.recycleView1;


        for (String string : list) {
            listArrayList.add(new ListModel(R.drawable.profile, string));
        }



        recycleView1.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter listAdapter = new ListAdapter(listArrayList,this);
        recycleView1.setAdapter(listAdapter);
    }

}
