package com.sterlitepower.vendorcom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this, login.class);
        startActivity(intent);  // calling activity from mainactivity to login activity
        finish(); // destroying mainactivity because when use click back button android should not show this activity
        // so need to destroy the activity from backgroud
    }
}
