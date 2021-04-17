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

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCorrectData()) {

                    service.signEmail(null)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AuthUserResponse>() {
                                @Override
                                public void onSubscribe(@androidx.annotation.NonNull Disposable d) {

                                }

                                @Override
                                public void onNext(@androidx.annotation.NonNull AuthUserResponse authUserResponse) {
                                    if(authUserResponse.getStatus().equals("success")) {
                                        transitionMainActivity();
                                    }
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });


                }
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
        Intent intent = new Intent(InputSmsActivity.this, IntroActivity.class);
        startActivity(intent);
    }
}