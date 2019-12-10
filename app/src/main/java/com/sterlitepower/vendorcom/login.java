package com.sterlitepower.vendorcom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    Button login;
    EditText phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Remember always leave Mainactivty empty, donot intialise any code in main activity because i'll tell later.

        login=findViewById(R.id.btn_login);  // mapping variable login with button in layout file i.e btn_login
        phone=findViewById(R.id.in_phone);   // mapping variable phone with text phone in layout file i.e in_phone
        password=findViewById(R.id.in_password); // mapping variable password with text password in layout file i.e in_password


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when user click on login button then this code will execute
                /*You need to create a condition that text fields of phone and password should not
                be empty and if not empty then call api and if response is ok or status code is 200
                then promt to next activity otherwise show the user toast notification with error
                * */
            }
        });

    }
}
