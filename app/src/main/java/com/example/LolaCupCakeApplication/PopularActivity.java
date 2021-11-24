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

public class PopularActivity extends AppCompatActivity {

    EditText txtID, txtName, txtPrice, txtDesc;
    ImageView picture;
    Spinner pcategory;
    DB_Operations db;
    Button Add,View;
    byte imageByte[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);

        db = new DB_Operations(this);

        txtID =(EditText) findViewById(R.id.txtIDp);
        txtName =(EditText) findViewById(R.id.txtNamep);
        txtPrice =(EditText) findViewById(R.id.txtPricep);
        txtDesc =(EditText) findViewById(R.id.txtDesp);
        picture =(ImageView) findViewById(R.id.pic1p);
        pcategory =(Spinner) findViewById(R.id.spinnerCp);
        Add =(Button) findViewById(R.id.btnaddpp);
        View =(Button) findViewById(R.id.btnViewpp);


        //Create an ArrayAdapter using the string array and a default spinner custom_product
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        //Specify the custom_product to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        pcategory.setAdapter(adapter);

        picture.setOnClickListener(new View.OnClickListener() {
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

                Popular popular = new Popular();
                try {
                    popular.setpId(Integer.parseInt(txtID.getText().toString()));
                    popular.setpName(txtName.getText().toString());
                    popular.setpPrice(txtPrice.getText().toString());
                    popular.setpCategory(pcategory.getSelectedItem().toString());
                    popular.setpDesc(txtDesc.getText().toString());
                    popular.setpImg(imageByte);
                    if (db.insert_popular(popular) > 0) {
                        Toast.makeText(PopularActivity.this, "Product Inserted!", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Log.e("Error", e.getMessage());
                }
            }
        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(getApplicationContext(), DisplayPopular.class);
                startActivity(i);
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
                    picture.setImageBitmap(bitmap);

                }catch(IOException e){
                    Log.e("Error : ", e.getMessage());
                }
            }
        }
    }
    public void getBack(View view) {

    }
}