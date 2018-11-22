package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.fragments.ActivityFragmentCuentas;
import com.simarro.practicas.goliath_national_bank.fragments.Activity_Fragment_Movimientos;
import com.simarro.practicas.goliath_national_bank.fragments.CuentasListener;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

import java.util.ArrayList;

public class Operaciones_Activity extends AppCompatActivity implements CuentasListener {
    private ArrayList<Cuenta> cuentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones);
        ActivityFragmentCuentas frgCuentas = (ActivityFragmentCuentas) getSupportFragmentManager().findFragmentById(R.id.listaCuentas);
        frgCuentas.setCuentasListener(this);

    }

    @Override
    public void onCuentaSeleccionada(Cuenta c) {
/*
        ArrayList<Movimiento> movimientos = c.getListaMovimientos();
        Intent movs = new Intent(this, Activity_Fragment_Movimientos.class);
        movs.putExtra("Cuenta",c);
        startActivity(movs);
        */
    }

}
