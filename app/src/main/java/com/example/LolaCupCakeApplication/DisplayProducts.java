package com.example.LolaCupCakeApplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayProducts extends AppCompatActivity {
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);

        ListView prdListView = (ListView) findViewById(R.id.productListView);

        DB_Operations db = new DB_Operations(this);

        try {
            products = db.viewAllProducts();
            if (products.size() > 0) {
                ProductAdapter productAdapter = new ProductAdapter(this, products);
                prdListView.setAdapter(productAdapter);

            } else {
                Toast.makeText(this, "No Products", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Error :", e.getMessage());
        }
    }

    public void getBack(View view) {
        Intent i = new Intent(getApplicationContext(), ALLprd.class);
        startActivity(i);
    }

    public void placeOrder(View view) {
//        int index = (int) view.getTag();
        Intent i = new Intent(DisplayProducts.this,OrderDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("choices",choices);
        bundle.putString("prices",prices);
        i.putExtras(bundle);
        startActivity(i);

    }
    String choices = "";
    String prices = "";

    public void getCupCake(View view) {
        int index = (int) view.getTag();
        Toast toast = Toast.makeText(this, "*****PRODUCTS DETAILS****\n" + products.get(index).getPrdId() +
                "\n" + products.get(index).getpName(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
        choices = choices+products.get(index).getpName();

        prices = prices+products.get(index).getpPrice();
    }
}