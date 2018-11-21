package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.bd.MiBancoOperacional;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;

public class CambioPass extends AppCompatActivity implements View.OnClickListener {
    private Cliente cliente;
    private MiBancoOperacional bancoOperacional;
    private String contrasenya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_pass);
        bancoOperacional = MiBancoOperacional.getInstance(this);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

    }

    @Override
    public void onClick(View v) {
        EditText viejaContrasenya = (EditText) findViewById(R.id.oldPassInsert);
        EditText nuevaContrasenya = (EditText) findViewById(R.id.newPassInsert);
        EditText nuevaRepetida = (EditText) findViewById(R.id.repeatedInsert);


        if (viejaContrasenya.getText().toString().equals(cliente.getClaveSeguridad())){

            if(nuevaContrasenya.getText().toString().equals(nuevaRepetida.getText().toString())){
                String valor = nuevaContrasenya.getText().toString();
                cliente.setClaveSeguridad(valor);
                int resultado = bancoOperacional.changePassword(cliente);
                if (resultado==1){

                    Intent menu = new Intent(CambioPass.this, MenuActivity.class);
                    menu.putExtra("Cliente", cliente);
                    startActivity(menu);
                }else{
                    Toast.makeText(this, "No se ha podido cambiar la contraseña", Toast.LENGTH_SHORT).show();
                }
                
                
            }else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "La contraseña vieja es erronea", Toast.LENGTH_SHORT).show();
        }


    }
}
