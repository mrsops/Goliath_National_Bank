package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent menu = new Intent(this, MenuActivity.class);
        menu.putExtra("Cliente", cliente);
        startActivity(menu);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings_toolbar:
                Toast.makeText(this, "Esto de momento no esta implementado", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.change_pass_toolbar:
                Intent changePass = new Intent(this, CambioPass.class);
                changePass.putExtra("Cliente", cliente);
                startActivity(changePass);
                finish();
                return true;

            case R.id.close_sesion_toolbar:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                finish();
                return true;

            case R.id.operations_toolbar:
                Intent transferencias = new Intent(this, TransferenciaActivity.class);
                transferencias.putExtra("Cliente", cliente);
                startActivity(transferencias);
                finish();
                return true;

            case R.id.pos_global_toolbar:
                Intent posGlobalActivity = new Intent(this, PosGlobalActivity.class);
                posGlobalActivity.putExtra("Cliente", cliente);
                startActivity(posGlobalActivity);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }
}
