package com.example.LolaCupCakeApplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PopularAdapter extends BaseAdapter {

    Context context;
    TextView pIdp,pNamep,pDesp,pPricep;
    ImageView imageView;


    ArrayList<Popular> populars;

    public PopularAdapter(Context context,ArrayList<Popular> populars){
        this.context = context;
        this.populars = populars;
        
    }

    @Override
    public int getCount() {
        return populars.size();
    }

    @Override
    public Object getItem(int i) {
        return populars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_product, viewGroup, false);

        pIdp =(TextView) v.findViewById(R.id.lbl_pID);
        pNamep =(TextView) v.findViewById(R.id.lbl_pName);
        imageView =(ImageView) v.findViewById(R.id.lbl_pic);
        pPricep =(TextView) v.findViewById(R.id.lbl_price);
        pDesp =(TextView) v.findViewById(R.id.lbl_des);

        Popular popular = populars.get(i);
        pIdp.setText(""+popular.getpId());
        pNamep.setText(popular.getpName());
        Bitmap bitmap = BitmapFactory.decodeByteArray(popular.getpImg(),0,popular.getpImg().length);
        imageView.setImageBitmap(bitmap);
        pPricep.setText(popular.getpPrice());
        pDesp.setText(popular.getpDesc());

        return v;
    }
}
