package com.example.LolaCupCakeApplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
//    ArrayList<ItemModel> itemModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ListView productScrollView = (ListView) findViewById(R.id.listCategory);
//        DBHandler2 db = new DBHandler2(this);
//
//        try{
//
//            itemModels = db.viewAllProducts();
//            if(itemModels.size()>0){
//                ProductAdapter2 productAdapter2 = new ProductAdapter2(this,itemModels);
//                productScrollView.setAdapter(productAdapter2);
//            }else{
//
//                Toast.makeText(this,"No Charactors include in this time", Toast.LENGTH_LONG).show();
//
//            }
//        }catch(Exception e){
//            Log.e("Error :", e.getMessage());
//        }

    }
}