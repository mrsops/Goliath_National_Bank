package com.simarro.practicas.goliath_national_bank.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.fragments.Activity_Fragment_Movimientos;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;

public class VerMovimientosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_movimientos);

        Cuenta c = (Cuenta) getIntent().getSerializableExtra("Cuenta");
        
        mostrarMovimientos(c);

    }

    public void mostrarMovimientos(Cuenta c){
        Activity_Fragment_Movimientos fragMovs = (Activity_Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FragmentoMovimientos);
        if (fragMovs==null){
            Toast.makeText(this, "Esto esta a null", Toast.LENGTH_SHORT).show();
        }
        fragMovs.mostrarMovimientos(c);

    }
}
