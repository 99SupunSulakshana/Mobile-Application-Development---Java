package com.example.LolaCupCakeApplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DataBase.DBHandler;

public class ProfileManagement extends AppCompatActivity {

    EditText username, dob, password;
    Button add, updateProfile;
    RadioButton male, female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.etUserNamePM);
        dob = findViewById(R.id.etbdyPM);
        password = findViewById(R.id.etPasswordPM);
        add = findViewById(R.id.btn_addPM);
        updateProfile = findViewById(R.id.btn_updatePM);
        male = findViewById(R.id.malePM);
        female = findViewById(R.id.femalePM);

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){
                    gender = "Male";
                }else{
                    gender = "Female";
                }
                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addInfo(username.getText().toString(),dob.getText().toString(), password.getText().toString(),gender);
                Toast.makeText(ProfileManagement.this, "User Added. User ID: "+newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(),EditProfile.class);// Success unata passe edit profile ekata yawanna wenawa
                startActivity(i);
                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);
            }
        });

    }
}