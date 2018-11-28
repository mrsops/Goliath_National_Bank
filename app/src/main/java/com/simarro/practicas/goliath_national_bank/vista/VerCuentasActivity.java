package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.fragments.ActivityFragmentCuentas;
import com.simarro.practicas.goliath_national_bank.fragments.Activity_Fragment_Movimientos;
import com.simarro.practicas.goliath_national_bank.fragments.CuentasListener;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

import java.util.ArrayList;

public class VerCuentasActivity extends AppCompatActivity implements CuentasListener {
    private ArrayList<Cuenta> cuentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_cuentas_activity);
        ActivityFragmentCuentas frgCuentas = (ActivityFragmentCuentas) getSupportFragmentManager().findFragmentById(R.id.FrgCuentas);
        if( frgCuentas == null){
            Toast.makeText(this, "No se ha retornado nada, el fragment es null", Toast.LENGTH_SHORT).show();
        }else{
            frgCuentas.setCuentasListener(this);
        }



        //frgCuentas.mostrarCuentas();

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }


    @Override
    public void onCuentaSeleccionada(Cuenta c) {

        Toast.makeText(this, "Se ha pulsado "+c.getNumeroCuenta(), Toast.LENGTH_SHORT).show();
        cambiarMovimientos(c);



    }


    public void cambiarMovimientos(Cuenta c){
        Activity_Fragment_Movimientos fragmentMovs = (Activity_Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgMovimientos);
        if(fragmentMovs == null){
            Intent movs = new Intent(this, VerMovimientosActivity.class);
            movs.putExtra("Cuenta", c);
            startActivity(movs);
        }else{
            fragmentMovs.mostrarMovimientos(c);
        }
    }

}
