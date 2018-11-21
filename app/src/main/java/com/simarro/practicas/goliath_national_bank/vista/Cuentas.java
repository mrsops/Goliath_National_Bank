package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.adapters.AdaptadorCustomizado;
import com.simarro.practicas.goliath_national_bank.bd.MiBancoOperacional;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;

import java.util.ArrayList;

public class Cuentas extends AppCompatActivity{
    private ArrayList<Cuenta> cuentas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuentas);
        ListView lista = (ListView)findViewById(R.id.listaCuentas);

        Cliente cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        MiBancoOperacional bancoOperacional = MiBancoOperacional.getInstance(this);
        cliente = bancoOperacional.login(cliente);

        cuentas = bancoOperacional.getCuentas(cliente);

        String numCuentas[]=new String[cuentas.size()];
        for (int i=0;i<numCuentas.length;i++){
            numCuentas[i]=cuentas.get(i).getNumeroCuenta();
        }

        AdaptadorCustomizado adaptador = new AdaptadorCustomizado(this, android.R.layout.simple_list_item_1, numCuentas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Cuenta c = cuentas.get(position);
                Intent movimientos = new Intent(Cuentas.this, Movimientos.class);
                movimientos.putExtra("Cuenta", c);
                startActivity(movimientos);

            }
        });
    }


}
