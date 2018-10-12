package com.simarro.practicas.goliath_national_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;


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
            if(v.getTag().equals("validar") && usuario.equals("usuario") && pass.equals("usuario") ){
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }else{
                Toast.makeText(this, "Usuario: "+usuario, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Contraseña: "+pass, Toast.LENGTH_SHORT).show();
            }

        }

    }
