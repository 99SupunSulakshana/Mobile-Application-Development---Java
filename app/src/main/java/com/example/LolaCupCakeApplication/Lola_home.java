package com.example.LolaCupCakeApplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;

public class Lola_home extends AppCompatActivity {

private final int ID_HOME = 1;
private final int ID_ADDTOCART = 2;
private final int ID_SEARCH = 3;
private final int ID_ABOUT = 4;
private final int ID_CONTACT = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lola_home);

        final TextView selected_page = findViewById(R.id.selected_page);
        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigtion);
//        bottomNavigation.setSelectedItemId(R.id.home);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ADDTOCART,R.drawable.ic_baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_SEARCH,R.drawable.ic_baseline_search_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ABOUT,R.drawable.ic_baseline_perm_device_information_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_CONTACT,R.drawable.ic_baseline_help_24));

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(Lola_home.this, "clicked Item : " + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });



        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
//                String name;
                switch (item.getId()){
                    case ID_HOME:

                    break;

                    case ID_ADDTOCART:
                        startActivity(new Intent(getApplicationContext(),DisplayProducts.class));
                        finish();
                        overridePendingTransition(0,0);
                       break;

                    case ID_SEARCH:
                        startActivity(new Intent(getApplicationContext(),ProfileManagement.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case ID_ABOUT:
                        startActivity(new Intent(getApplicationContext(),ALLprd.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    case ID_CONTACT:
                        startActivity(new Intent(getApplicationContext(),ProductOperations.class));
                        finish();
                        overridePendingTransition(0,0);
                        break;

                    default:
                }

//                selected_page.setText(getString(R.string.main_page_selected, name));


            }
        });

        bottomNavigation.setCount(ID_ADDTOCART,"4");
        bottomNavigation.show(ID_HOME,true);

        ListView prdListView =(ListView)findViewById(R.id.productPopular);
        ArrayList<Product> products;
        DB_Operations db = new DB_Operations(this);

        try{
            products = db.viewAllProducts();
            if(products.size()>0){
                ProductAdapter productAdapter = new ProductAdapter(this,products);
                prdListView.setAdapter(productAdapter);

            }else{
                Toast.makeText(this, "No Products", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Log.e("Error :",e.getMessage());
        }

        ListView Allprd1 =(ListView)findViewById(R.id.productAll);

        try{
            products = db.viewAllProducts();
            if(products.size()>0){
                ProductAdapter productAdapter = new ProductAdapter(this,products);
                Allprd1.setAdapter(productAdapter);

            }else{
                Toast.makeText(this, "No Products", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Log.e("Error :",e.getMessage());
        }

        ListView prdListView1 =(ListView)findViewById(R.id.productSpecial);
//        ArrayList<Product> products;
//        DB_Operations db = new DB_Operations(this);

        try{
            products = db.viewAllProducts();
            if(products.size()>0){
                ProductAdapter productAdapter = new ProductAdapter(this,products);
                prdListView1.setAdapter(productAdapter);

            }else{
                Toast.makeText(this, "No Products", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Log.e("Error :",e.getMessage());
        }

    }


    public void ViewSp(View view) {

    }

    public void ViewAll(View view) {
    }

}