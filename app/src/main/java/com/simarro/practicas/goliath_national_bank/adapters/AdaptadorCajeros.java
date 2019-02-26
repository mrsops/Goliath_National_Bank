package com.simarro.practicas.goliath_national_bank.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.simarro.practicas.banco_aitor.dao.CajeroDAO;

import static com.simarro.practicas.banco_aitor.pojo.Constantes.FIELD_DIRECCION;

public class AdaptadorCajeros extends CursorAdapter {

    private CajeroDAO cajeroDAO = null;

    public AdaptadorCajeros(Context context, Cursor cursor) {
        super(context, cursor, 0);
        cajeroDAO = new CajeroDAO(context);
        cajeroDAO.abrir();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        return view;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView) view;
        int i = cursor.getColumnIndex(FIELD_DIRECCION);
        tv.setText(cursor.getString(i));

    }
}
