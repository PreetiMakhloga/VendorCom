package com.sterlitepower.vendorcom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

        Gson gson=new Gson();

        final ProgressDialog progressDialog=new ProgressDialog(this); // Progress dialog for on screen message while loading

        //Retrofit api builders
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://example.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        final retrofit_login login_ret=retrofit.create(retrofit_login.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when user click on login button then this code will execute
                /*You need to create a condition that text fields of phone and password should not
                be empty and if not empty then call api and if response is ok or status code is 200
                then promt to next activity otherwise show the user toast notification with error
                **/
                String user_phone="+91";
                String user_password=password.getText().toString();
                user_phone=user_phone+phone.getText().toString();
                if(!user_password.isEmpty()&&!user_phone.isEmpty()){
                    usr_Credenials credenials=new usr_Credenials(user_phone,user_password);
                    Gson gson_convert=new Gson();
                    String json_data=gson_convert.toJson(credenials);
                    Call<Object> login_call=login_ret.login(json_data);

                    // Setting up Progress dialog title and message
                    progressDialog.setTitle("Please wait");
                    progressDialog.setMessage("Logging in.......");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();


                    //Calling api
                    login_call.enqueue(new Callback<Object>() {

                        @Override
                        // when response from api this method will execute
                        public void onResponse(Call<Object> call, Response<Object> response) {

                            //checks whether status code is 200 or not?

                            if(response.code()==200){
                                //Trying to import token from the response body of the post call
                                LinkedTreeMap response_tree_api=(LinkedTreeMap)response.body();
                                //Storing it into string
                                String response_token=((LinkedTreeMap)response_tree_api.get("response")).get("token").toString();

                                // using shared preferences to acess that stored token in another activities
                                SharedPreferences  sharedPreferences=getSharedPreferences("vendorapp", Context.MODE_PRIVATE);
                                sharedPreferences.edit().putString("token",response_token).apply();
                                //notification
                                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent intent=new Intent(login.this,vendor_form.class);
                                startActivity(intent);
                                finish();
                            }
                            else if(response.code()!=200){
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Login Failed"+response.message(),Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Failed! Something went wrong",Toast.LENGTH_SHORT);
                        }
                    });



                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Please Enter all the details",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public class usr_Credenials{
        private String phone;
        private String password;
        public usr_Credenials(String phone, String password){
            this.password=password;
            this.phone=phone;
        }
    }
}
