package com.example.LolaCupCakeApplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DataBase.DBHandler;

import java.util.ArrayList;

public class UserView extends AppCompatActivity {

    ListView userList;
    ArrayList dataList;
    ArrayAdapter adapter;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        userList = findViewById(R.id.list_view_users);

        db= new DBHandler(getApplicationContext());
        dataList = db.readAllInfo();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList);

        userList.setAdapter(adapter);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                //String text= userList.getItemAtPosition(i).toString();
                //Toast.makeText(UserView.this, "User Details:"+text,Toast.LENGTH_SHORT).show();

                final int which_item = position;

                new AlertDialog.Builder(UserView.this)
                        .setIcon(R.drawable.ic_users)
                        .setTitle("LOLA USER ACCOUNTS")
                        .setMessage("Do you want to Remove this information?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataList.remove(which_item);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
            }
        }
        );
    }
}