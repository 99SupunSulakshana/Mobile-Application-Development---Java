package com.example.LolaCupCakeApplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayPopular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_popular);
        ListView prdListView =(ListView)findViewById(R.id.popularListView);
        ArrayList<Popular> populars;
        DB_Operations db = new DB_Operations(this);

        try{
            populars = db.viewAllPopular();
            if(populars.size()>0){
                PopularAdapter popularAdapter = new PopularAdapter(this,populars);
                prdListView.setAdapter(popularAdapter);

            }else{
                Toast.makeText(this, "No Products", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Log.e("Error :",e.getMessage());
        }

    }

    public void getBack(View view) {
    }
}