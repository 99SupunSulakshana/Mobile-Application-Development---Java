package com.example.LolaCupCakeApplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    Context context;
    TextView pId,pName,pDes,pPrice;
    Button getItem;
    ImageView imageView;

    ArrayList<Product> products;

    public ProductAdapter(Context context,ArrayList<Product> products){
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_product, viewGroup, false);

        pId =(TextView) v.findViewById(R.id.lbl_pID);
        pName =(TextView) v.findViewById(R.id.lbl_pName);
        imageView =(ImageView) v.findViewById(R.id.lbl_pic);
        pPrice =(TextView) v.findViewById(R.id.lbl_price);
        pDes =(TextView) v.findViewById(R.id.lbl_des);
        getItem=(Button) v.findViewById(R.id.btnGetItem);

        getItem.setTag(i);


        Product product = products.get(i);
        pId.setText(""+product.getPrdId());
        pName.setText(product.getpName());
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImg(),0,product.getImg().length);
        imageView.setImageBitmap(bitmap);
        pPrice.setText(product.getpPrice());
        pDes.setText(product.getDesc());

        return v;
    }
}
