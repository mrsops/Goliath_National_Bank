package com.simarro.practicas.goliath_national_bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import static com.simarro.practicas.goliath_national_bank.Controlador.*;

import com.simarro.practicas.goliath_bank.R;


    public class MainActivity extends AppCompatActivity implements View.OnClickListener {


        private String usuario;
        private String pass;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            this.usuario=usuario;
            this.pass=pass;

            Banco banco = new Banco();

            Cuenta cuenta = new Cuenta("usuari", "usuari");
            banco.add(cuenta);
            asignarBanco(banco);

        }

        @Override
        public void onClick(View v) {
            EditText edUser = (EditText) findViewById(R.id.userInsert);
            EditText edPass = (EditText) findViewById(R.id.passInsert);
            usuario = edUser.getText().toString();
            pass = edPass.getText().toString();
            Banco b = getBanco();

            if(getBanco().comprobarAcceso(usuario, pass) != null){
                accederCuenta(getBanco().comprobarAcceso(usuario, pass));
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }else{
                Toast.makeText(this, "Usuario: "+usuario, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Contraseña: "+pass, Toast.LENGTH_SHORT).show();
            }



        }

    }
