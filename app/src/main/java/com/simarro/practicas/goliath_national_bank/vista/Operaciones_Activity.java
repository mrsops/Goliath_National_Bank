package com.simarro.practicas.goliath_national_bank.vista;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.fragments.ActivityFragmentCuentas;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;

import java.util.ArrayList;

public class Operaciones_Activity extends FragmentActivity implements ActivityFragmentCuentas.CuentasListener {
    private ArrayList<Cuenta> cuentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operaciones);
        ActivityFragmentCuentas frgCuentas = (ActivityFragmentCuentas) getSupportFragmentManager().findFragmentById(R.id.listaCuentas);
        frgCuentas.setCuentasListener(this);


        //frgCuentas.mostrarCuentas();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
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
