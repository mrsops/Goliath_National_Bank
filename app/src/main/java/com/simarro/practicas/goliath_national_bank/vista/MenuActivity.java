package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.bd.MiBancoOperacional;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Cliente cliente;
    private MiBancoOperacional bancoOperacional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        bancoOperacional = MiBancoOperacional.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        Button clave = findViewById(R.id.change_pass);
        Button operaciones = findViewById(R.id.operations);
        Button verCuentas = findViewById(R.id.show_accounts);
        Button cerrarSesion = findViewById(R.id.close_sesion);
        Button posGlobal = findViewById(R.id.pos_global);
        Button ingresos = findViewById(R.id.income);

        if (clave.getId()== v.getId()){
            Intent menu = new Intent(MenuActivity.this, CambioPass.class);
            menu.putExtra("Cliente", cliente);
            startActivity(menu);

        }else if(verCuentas.getId()==v.getId()){

            Intent cuentas = new Intent(this, Cuentas.class);
            cuentas.putExtra("Cliente", cliente);
            startActivity(cuentas);
            Toast.makeText(this, "Esto funciona", Toast.LENGTH_SHORT).show();

        }else if(cerrarSesion.getId()==v.getId()){
            Intent menu = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(menu);

        }else if(posGlobal.getId()==v.getId()){


        }else if(ingresos.getId()==v.getId()){
            Toast.makeText(MenuActivity.this, cliente.getNombre()+" "+this.cliente.getClaveSeguridad(), Toast.LENGTH_LONG).show();

        }else if(operaciones.getId()==v.getId()){
            startActivity(new Intent(MenuActivity.this, TransferenciaActivity.class));
        }else{

        }

    }
}
