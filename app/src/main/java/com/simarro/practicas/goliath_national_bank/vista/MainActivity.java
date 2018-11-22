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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private String usuario;
        private String pass;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        @Override
        public void onClick(View v) {
            EditText edUser = (EditText) findViewById(R.id.userInsert);
            EditText edPass = (EditText) findViewById(R.id.passInsert);
            usuario = edUser.getText().toString();
            pass = edPass.getText().toString();
            MiBancoOperacional miBOp = MiBancoOperacional.getInstance(this);
            Cliente cliente = new Cliente();
            cliente.setNif(usuario);
            cliente.setClaveSeguridad(pass);
            if (miBOp.login(cliente)!=null){
                Intent menu = new Intent(MainActivity.this, MenuActivity.class);
                cliente = miBOp.login(cliente);
                menu.putExtra("Cliente", cliente);

                startActivity(menu);
                finish();
            }else{
                Toast.makeText(this, "Usuario y contraseña erroneos. ", Toast.LENGTH_SHORT).show();
            }



        }

    }
