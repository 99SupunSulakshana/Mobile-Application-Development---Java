package com.example.LolaCupCakeApplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ALLprd extends AppCompatActivity {

    EditText txtID, txtName, txtPrice, txtDesc;
    ImageView pic;
    Spinner category;
    DB_Operations db;
    Button Add,View;
    byte imageByte[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_l_lprd);
        db = new DB_Operations(this);

        txtID =(EditText) findViewById(R.id.AdverticeID);
        txtName =(EditText) findViewById(R.id.devryName);
        txtPrice =(EditText) findViewById(R.id.textOrderpName);
        txtDesc =(EditText) findViewById(R.id.txtDes);
        pic =(ImageView) findViewById(R.id.pic1);
        category =(Spinner) findViewById(R.id.spinnerC);
        Add =(Button) findViewById(R.id.btnaddp);
          View =(Button) findViewById(R.id.btnViewp);


        //Create an ArrayAdapter using the string array and a default spinner custom_product
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        //Specify the custom_product to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        category.setAdapter(adapter);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("crop","true");
                intent.putExtra("aspectX",0);
                intent.putExtra("aspectY",0);
                intent.putExtra("outputX",200);
                intent.putExtra("outputY",200);
                intent.putExtra("return-data","true");
                startActivityForResult(Intent.createChooser(intent,"SELECT IMAGE"),111);
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Product product = new Product();
                try {
                    product.setPrdId(Integer.parseInt(txtID.getText().toString()));
                    product.setpName(txtName.getText().toString());
                    product.setpPrice(txtPrice.getText().toString());
                    product.setpCategory(category.getSelectedItem().toString());
                    product.setDesc(txtDesc.getText().toString());
                    product.setImg(imageByte);
                    if (db.insert_product(product) > 0) {
                        Toast.makeText(ALLprd.this, "Product Inserted!", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Log.e("Error", e.getMessage());
                }
            }
        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(ALLprd.this,DisplayProducts.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 111){
            if( data !=null){
                Uri uri = data.getData();

                try{

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,0, arrayOutputStream);
                    imageByte = arrayOutputStream.toByteArray();
                    pic.setImageBitmap(bitmap);

                }catch(IOException e){
                    Log.e("Error : ", e.getMessage());
                }
            }
        }
    }

    public void getBack(android.view.View view) {
        Intent i = new Intent(getApplicationContext(), ALLprd.class);
        startActivity(i);
    }

//    public void displayProduct(View view){
//        Intent intent = new Intent(ALLprd.this,DisplayProducts.class);
//                startActivity(intent);
//    }
}