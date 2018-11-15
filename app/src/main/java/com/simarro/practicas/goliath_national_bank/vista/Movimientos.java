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
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

import java.util.ArrayList;

public class Movimientos extends AppCompatActivity {
    private ArrayList<Movimiento> movimientos;
    private Cuenta cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Algo", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_movimientos);
        String[] datos = {"Elemento 1", "Elemento2"};

        ListView lista = (ListView)findViewById(R.id.listaCuentas);

        cuenta = (Cuenta) getIntent().getSerializableExtra("Cuenta");
        MiBancoOperacional bancoOperacional = MiBancoOperacional.getInstance(this);
        movimientos = bancoOperacional.getMovimientos(cuenta);


        String movs[]=new String[movimientos.size()];
        for (int i=0;i<movs.length;i++){
            movs[i]=movimientos.get(i).getImporte()+"";
        }
        Toast.makeText(this, cuenta.getNumeroCuenta(), Toast.LENGTH_SHORT).show();

        //AdaptadorCustomizado adaptador = new AdaptadorCustomizado(this, android.R.layout.simple_list_item_1, datos);
        //lista.setAdapter(adaptador);


    }

}
