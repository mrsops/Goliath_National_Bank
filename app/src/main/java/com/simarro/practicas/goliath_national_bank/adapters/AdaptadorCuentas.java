package com.simarro.practicas.goliath_national_bank.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;

import java.util.ArrayList;

public class AdaptadorCuentas extends ArrayAdapter<Cuenta>{
    Activity context;
    ArrayList<Cuenta> cuentas;

    public AdaptadorCuentas(Fragment context, ArrayList<Cuenta> cuentas) {
        super(context.getActivity(), R.layout.);
        this.cuentas = cuentas;
        this.context = context.getActivity();
    }
}
