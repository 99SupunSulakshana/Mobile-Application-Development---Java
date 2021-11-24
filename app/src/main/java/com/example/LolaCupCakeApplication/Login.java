package com.example.LolaCupCakeApplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DataBase.DBHandler;


public class Login extends AppCompatActivity {


    EditText username, password;
    Button login, registration,dataView,dataview2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       username = findViewById(R.id.etUserNameH);
       password = findViewById(R.id.etPasswordH);
       login = findViewById(R.id.btn_check);
       registration = findViewById(R.id.btn_registerH);
       dataView= findViewById(R.id.btnview);
       dataview2 = findViewById(R.id.btnview1);
//       Popular = findViewById(R.id.btnPopular);
//       Special = findViewById(R.id.btnSpecial);


//       Popular.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent i = new Intent(getApplicationContext(), Popular_Products.class);
//               startActivity(i);
//
//           }
//       });
//
//       Special.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent i = new Intent(getApplicationContext(), SpecialProducts.class);
//               startActivity(i);
//
//           }
//       });

       registration.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent i = new Intent(getApplicationContext(), Lola_home.class);
               startActivity(i);

           }
       });


        dataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),PopularActivity.class);
                startActivity(i);
            }
        });

        dataview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ALLprd.class);
                startActivity(i);
            }
        });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               DBHandler dbhandler = new DBHandler(getApplicationContext());
               if(dbhandler.loginUser(username.getText().toString(),password.getText().toString())){
                   Toast.makeText(Login.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(getApplicationContext(),EditProfile.class);
                   startActivity(i);
               }else{
                   Toast.makeText(Login.this, "User Login Unsuccessfully. Please enter correct details", Toast.LENGTH_SHORT).show();
                   username.setText(null);
                   password.setText(null);
               }
           }
       });

    }
}
