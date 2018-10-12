package com.simarro.practicas.goliath_national_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public void onClick(View v) {
        Button clave = findViewById(R.id.change_pass);
        Button operaciones = findViewById(R.id.operations);
        Button verCuentas = findViewById(R.id.show_accounts);
        Button cerrarSesion = findViewById(R.id.close_sesion);
        Button posicionGlobal = findViewById(R.id.pos_global);
        Button ingresos = findViewById(R.id.income);

        if (clave.getId()== v.getId()){
            startActivity(new Intent(MenuActivity.this, CambioPass.class));

        }else if(verCuentas.getId()==v.getId()){

        }else if(cerrarSesion.getId()==v.getId()){

        }else if(posicionGlobal.getId()==v.getId()){

        }else if(ingresos.getId()==v.getId()){

        }else if(operaciones.getId()==v.getId()){

        }else{
            Toast.makeText(this, "Pulsa un boton", Toast.LENGTH_SHORT);
        }

    }
}
