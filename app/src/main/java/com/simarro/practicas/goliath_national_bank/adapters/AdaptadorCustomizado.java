package com.simarro.practicas.goliath_national_bank.adapters;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorCustomizado extends ArrayAdapter<String> {

    public AdaptadorCustomizado(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = super.getView(position, convertView, parent);
        String font = "font/oregon_ldo_black.ttf";
        //Typeface fuente = Typeface.createFromFile(font);

        TextView tv = (TextView) view.findViewById(android.R.id.text1);
        tv.setTextColor(Color.parseColor("#e4e4e4"));
        //tv.setTypeface(fuente);

        return view;
    }
}