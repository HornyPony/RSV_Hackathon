package com.example.rsv_hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.rsv_hackathon.databinding.ActivityInputNumberBinding;

public class InputNumberActivity extends AppCompatActivity {

    private ActivityInputNumberBinding binding;
    private SharedPreferences sharedPreferences;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input_number);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        binding.editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                number = binding.editText2.getText().toString();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCorrectData()) transitionSmsActivity();
            }
        });
    }

    private boolean isCorrectData() {
        return number != null && !number.isEmpty();
    }

    private void transitionSmsActivity() {
        sharedPreferences.edit().putString("number", number).apply();
        Intent intent = new Intent(InputNumberActivity.this, InputSmsActivity.class);
        startActivity(intent);
    }
}