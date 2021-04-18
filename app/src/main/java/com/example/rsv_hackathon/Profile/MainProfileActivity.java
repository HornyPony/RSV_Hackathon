package com.example.rsv_hackathon.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rsv_hackathon.ChatBot.ChatActivity;
import com.example.rsv_hackathon.R;

public class MainProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView botIcon;
    private Button additionallyButton;
    private SharedPreferences sharedPreferences;
    private TextView profileNumberTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        botIcon = findViewById(R.id.matreshkaBot);
        additionallyButton = findViewById(R.id.additionallyButton);
        profileNumberTV = findViewById(R.id.profileNumberTV);

        sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);

        profileNumberTV.setText(sharedPreferences.getString("number", ""));

        botIcon.setOnClickListener(this);
        additionallyButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.matreshkaBot:
                startActivity(new Intent(MainProfileActivity.this, ChatActivity.class));
                break;
            case R.id.additionallyButton:
                startActivity(new Intent(MainProfileActivity.this, ProfileAdditionallyActivity.class));
                break;
        }
    }
}