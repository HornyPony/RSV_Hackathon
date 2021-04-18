package com.example.rsv_hackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.rsv_hackathon.Profile.MainProfileActivity;
import com.example.rsv_hackathon.databinding.ActivityInputSmsBinding;
import com.example.rsv_hackathon.network.Connection;
import com.example.rsv_hackathon.network.Service;
import com.example.rsv_hackathon.network.model.AuthUserResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InputSmsActivity extends AppCompatActivity {

    private ActivityInputSmsBinding binding;
    private SharedPreferences sharedPreferences;
    private String smsCode;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_input_sms);

        service = Connection.getInstance().getService();
        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);


        String string1 = "Введите код подтверждения из SMS-сообщения, отправленного на номер "
                + sharedPreferences.getString("number", "");
        binding.textView2.setText(string1);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    transitionMainActivity();

            }
        });

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                smsCode = binding.editText.getText().toString();
            }
        });
    }

    private boolean isCorrectData() {
        return smsCode != null && !smsCode.isEmpty();
    }

    private void transitionMainActivity() {
        Intent intent = new Intent(InputSmsActivity.this, MainProfileActivity.class);
        startActivity(intent);
    }
}