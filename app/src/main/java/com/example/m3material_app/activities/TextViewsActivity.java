package com.example.m3material_app.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.m3material_app.R;
import com.example.m3material_app.databinding.ActivityTextViewsBinding;
import com.google.android.material.textfield.TextInputLayout;

public class TextViewsActivity extends AppCompatActivity {
    ActivityTextViewsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTextViewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] programingLanguage = getResources().getStringArray(R.array.programming_languages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdownitems, programingLanguage);
        AutoCompleteTextView autoCompleteTextView = binding.autoTextView;
        autoCompleteTextView.setAdapter(arrayAdapter);
    TextInputLayout textView7 = binding.textField7;
    textView7.setError(getString(R.string.Close));

        textView7.setError(null);
}

}