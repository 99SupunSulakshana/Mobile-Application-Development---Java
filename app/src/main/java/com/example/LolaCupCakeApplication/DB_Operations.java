package com.example.LolaCupCakeApplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_Operations extends SQLiteOpenHelper {

    public DB_Operations(@Nullable Context context) {
        super(context,"db_lola",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql="CREATE TABLE tblUser(ID INTEGER PRIMARY KEY, NAME VARCHAR(25), EMAIL VARCHAR(25),PASS VARCHAR(15))";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE tblProduct(pID INTEGER PRIMARY KEY, pNAME VARCHAR(25), pPRICE VARCHAR(25), pCATEGORY VARCHAR(25), DES VARCHAR(125), IMG BLOG)";
        sqLiteDatabase.execSQL(sql);
        sql="CREATE TABLE tblPopular(pID INTEGER PRIMARY KEY, NAME VARCHAR(25), PRICE VARCHAR(25), CATEGORY VARCHAR(25), DES VARCHAR(125), IMG BLOG)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

     String sql = "DROP TABLE IF EXISTS tblUser";
     sqLiteDatabase.execSQL(sql);
     sql="DROP TABLE IF EXISTS tblProduct";
     sqLiteDatabase.execSQL(sql);
        sql="DROP TABLE IF EXISTS tblPopular";
        sqLiteDatabase.execSQL(sql);

    }


//add all products....................
    public long insert_product(Product product){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pID",product.getPrdId());
        contentValues.put("pNAME",product.getpName());
        contentValues.put("pPRICE",product.getpPrice());
        contentValues.put("pCATEGORY",product.getpCategory());
        contentValues.put("DES",product.getDesc());
        contentValues.put("IMG",product.getImg());

        return db.insert("tblProduct",null,contentValues);

    }


    //add popular items............
    public long insert_popular(Popular popular){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pID",popular.getpId());
        contentValues.put("NAME",popular.getpName());
        contentValues.put("PRICE",popular.getpPrice());
        contentValues.put("CATEGORY",popular.getpCategory());
        contentValues.put("DES",popular.getpDesc());
        contentValues.put("IMG",popular.getpImg());
        return db.insert("tblPopular",null,contentValues);

    }

    public ArrayList<Product> viewAllProducts(){
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM tblProduct";
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        ArrayList<Product> products = new ArrayList<Product>();
        if(count!=0){
            while (cursor.moveToNext()){
                Product product = new Product();
                product.setPrdId(cursor.getInt(0));
                product.setpName(cursor.getString(1));
                product.setpPrice(cursor.getString(2));
                product.setpCategory(cursor.getString(3));
                product.setDesc(cursor.getString(4));
                product.setImg(cursor.getBlob(5));
                products.add(product);
            }
        }
        return products;
    }

    public ArrayList<Popular> viewAllPopular(){
        SQLiteDatabase db = getReadableDatabase();
        String sql="SELECT * FROM tblPopular";
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        ArrayList<Popular> populars = new ArrayList<Popular>();
        if(count!=0){
            while (cursor.moveToNext()){
                Popular popular = new Popular();
                popular.setpId(cursor.getInt(0));
                popular.setpName(cursor.getString(1));
                popular.setpPrice(cursor.getString(2));
                popular.setpCategory(cursor.getString(3));
                popular.setpDesc(cursor.getString(4));
                popular.setpImg(cursor.getBlob(5));
                populars.add(popular);
            }
        }
        return populars;
    }
}
