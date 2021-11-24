package com.example.LolaCupCakeApplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDetails extends AppCompatActivity {

    TextView Orders,pricesView;
    String list_choice,priceRS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Orders = (TextView) findViewById(R.id.orderList);
        pricesView = (TextView) findViewById(R.id.priceView);

        //getting the passed value from displayproducts

        Bundle bundle = getIntent().getExtras();
        list_choice = bundle.getString("choices");
        priceRS = bundle.getString("prices");

        Orders.setText(list_choice);
        pricesView.setText(priceRS);

        
    }

    public void checkOut(View view) {

    }
}