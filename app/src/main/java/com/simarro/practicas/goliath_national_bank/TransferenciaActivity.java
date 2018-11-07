package com.simarro.practicas.goliath_national_bank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.simarro.practicas.goliath_bank.R;

public class TransferenciaActivity extends AppCompatActivity {
    private Spinner comboMonedas;
    private String[] tipoMonedas;
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);
        tipoMonedas = new String[]{"€", "$"};
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipoMonedas);
        comboMonedas = (Spinner) findViewById(R.id.combo);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboMonedas.setAdapter(adaptador);


    }
}
