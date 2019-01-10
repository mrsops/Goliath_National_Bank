package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;

public class TransferenciaActivity_old extends AppCompatActivity {
    private Spinner comboMonedas;
    private String[] tipoMonedas;
    private Cliente cliente;

    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia_old);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        tipoMonedas = new String[]{"€", "$"};
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoMonedas);
        comboMonedas = (Spinner) findViewById(R.id.combo);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboMonedas.setAdapter(adaptador);


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
                Intent transf = new Intent(this, TransferenciaActivity_old.class);
                transf.putExtra("Cliente",cliente);
                startActivity(transf);
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
