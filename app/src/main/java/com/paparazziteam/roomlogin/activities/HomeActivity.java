package com.paparazziteam.roomlogin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.paparazziteam.roomlogin.R;

public class HomeActivity extends AppCompatActivity {

    TextView txtmatricula;
    String mExtraMatricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mExtraMatricula = getIntent().getStringExtra("matricula");
        txtmatricula = findViewById(R.id.txtmatricula);

        txtmatricula.setText(mExtraMatricula);

    }
}