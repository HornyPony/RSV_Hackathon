package com.example.rsv_hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.rsv_hackathon.databinding.ActivityRegistrationBinding;
import com.example.rsv_hackathon.network.Connection;
import com.example.rsv_hackathon.network.Service;
import com.example.rsv_hackathon.network.model.AuthUserRequest;
import com.example.rsv_hackathon.network.model.AuthUserResponse;
import com.google.gson.Gson;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    private String firstName;
    private String secondName;
    private SharedPreferences sharedPreferences;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);

        service = Connection.getInstance().getService();

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        Gson gson = new Gson();

        AuthUserRequest request1 = new AuthUserRequest(
                sharedPreferences.getString("first_name", "qwe"),
                sharedPreferences.getString("second_name", "werewr"),
                sharedPreferences.getString("phone", "sdfsdg"),
                sharedPreferences.getString("code", "fdgfdg")
        );

//        String request = request1.toJSON();

        service.signEmail(request1.toJSON())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuthUserResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AuthUserResponse authUserResponse) {
                        if(authUserResponse.getStatus().equals("success")) {
                            Toast.makeText(RegistrationActivity.this, "sdf", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });





        initMainString();

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkCorrectData()) transitionNumberActivity();
            }
        });

    }

    private void initMainString(){
        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                firstName = binding.editText.getText().toString();
            }
        });

        binding.editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                secondName = binding.editText2.getText().toString();
            }
        });
    }

    private boolean checkCorrectData() {
        return firstName != null && secondName != null && !firstName.isEmpty() && !secondName.isEmpty();
    }

    private void transitionNumberActivity(){
        sharedPreferences.edit().putString("firstName", firstName).apply();
        sharedPreferences.edit().putString("secondName", secondName).apply();
        Intent intent = new Intent(RegistrationActivity.this, InputNumberActivity.class);
        startActivity(intent);
    }

}