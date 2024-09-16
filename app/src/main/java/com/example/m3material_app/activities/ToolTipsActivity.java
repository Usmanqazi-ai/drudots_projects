package com.example.m3material_app.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m3material_app.R;
import com.example.m3material_app.databinding.ActivityToolTipsBinding;

public class ToolTipsActivity extends AppCompatActivity {
ActivityToolTipsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityToolTipsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btn1 = binding.btn1;
        Button btn2 = binding.btn2;
        Button btn3 = binding.btn3;
        Button btn4 = binding.btn4;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            btn1.setTooltipText("Button1");
        } if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            btn2.setTooltipText("Button2");
        } if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            btn3.setTooltipText("Button3");
        } if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            btn4.setTooltipText("Button4");
        }
    }
}