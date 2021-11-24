package com.example.LolaCupCakeApplication;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class search extends AppCompatActivity {

    SearchView searchview;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchview = findViewById(R.id.productsSearch);
        recyclerView = findViewById(R.id.recycle);



    }
}