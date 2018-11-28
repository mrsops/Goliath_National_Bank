package com.simarro.practicas.goliath_national_bank.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;

import java.util.ArrayList;

public class AdaptadorCuentas extends ArrayAdapter<Cuenta>{
    Activity context;
    ArrayList<Cuenta> cuentas;

    public AdaptadorCuentas(Fragment context, ArrayList<Cuenta> cuentas) {
        super(context.getActivity(), R.layout.ver_cuentas_activity, cuentas);
        this.cuentas = cuentas;
        this.context = context.getActivity();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_elemento_cuentas, null);
        TextView lblCuentas = (TextView) item.findViewById(R.id.TextoCuenta);
        lblCuentas.setText("Nº Cuenta: "+cuentas.get(position).getNumeroCuenta());
        lblCuentas.setTextColor(Color.parseColor("#e4e4e4"));
        return item;
    }
}
