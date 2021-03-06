package com.simarro.practicas.goliath_national_bank.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

import java.util.ArrayList;
import java.util.Date;

public class AdaptadorMovimientos extends ArrayAdapter<Movimiento>{
    Activity context;
    ArrayList<Movimiento> movimientos;

    public AdaptadorMovimientos(Fragment context, ArrayList<Movimiento> movimientos) {
        super(context.getActivity(), R.layout.activity_ver_movimientos, movimientos);
        this.movimientos = movimientos;
        this.context = context.getActivity();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_elemento_movimiento, null);

        TextView lblImporte = (TextView) item.findViewById(R.id.LblImporte);
        lblImporte.setText("Importe: "+movimientos.get(position).getImporte());

        TextView lblFecha = (TextView) item.findViewById(R.id.LblFecha);
        Date date = movimientos.get(position).getFechaOperacion();
        String fecha = date.getDate()+"/"+(date.getMonth()+1)+"/"+(date.getYear()+1900)+"  "+date.getHours()+":"+date.getMinutes();
        lblFecha.setText("Fecha: "+fecha);

        TextView lblDescripcion = (TextView) item.findViewById(R.id.LblDescripcion);
        lblDescripcion.setText("Asunto: "+movimientos.get(position).getDescripcion());



        return item;
    }
}
