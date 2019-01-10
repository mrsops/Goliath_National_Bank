package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.fragments.ActivityFragmentCuentas;
import com.simarro.practicas.goliath_national_bank.fragments.Activity_Fragment_Movimientos;
import com.simarro.practicas.goliath_national_bank.fragments.CuentasListener;
import com.simarro.practicas.goliath_national_bank.fragments.Dialogo;
import com.simarro.practicas.goliath_national_bank.fragments.MovimientosListener;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

import java.util.ArrayList;

public class PosGlobalActivity extends AppCompatActivity implements CuentasListener, MovimientosListener {
    private ArrayList<Cuenta> cuentas;
    private Cliente cliente;
    private Activity_Fragment_Movimientos fragmentMovs;
    private ActivityFragmentCuentas frgCuentas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_cuentas_activity);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        this.frgCuentas = (ActivityFragmentCuentas) getSupportFragmentManager().findFragmentById(R.id.FrgCuentas);
        this.fragmentMovs = (Activity_Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgMovimientos);

        if( this.frgCuentas != null){
            this.frgCuentas.setCuentasListener(this);
        }else{
            Toast.makeText(this, "No se ha retornado nada, el fragment es null", Toast.LENGTH_SHORT).show();

        }



        //frgCuentas.mostrarCuentas();

    }


    @Override
    public void onCuentaSeleccionada(Cuenta c) {
        cambiarMovimientos(c);
    }


    public void cambiarMovimientos(Cuenta c){
        if(this.fragmentMovs == null){
            Intent movs = new Intent(this, VerMovimientosActivity.class);
            movs.putExtra("Cuenta", c);
            movs.putExtra("Cliente", cliente);
            startActivity(movs);
        }else{
            fragmentMovs.mostrarMovimientos(c);
            fragmentMovs.setMovimientosListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        Intent menu = new Intent(this, MenuActivity.class);
        menu.putExtra("Cliente", cliente);
        startActivity(menu);
        finish();
    }

    @Override
    public void onMovimientoSeleccionado(Movimiento m) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        Dialogo dialogo = Dialogo.newInstance((Movimiento) m);
        dialogo.show(fragmentManager, "tagConfirmacion");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
                this.finish();
                return true;

            case R.id.operations_toolbar:
                Intent transferencias = new Intent(this, Transferencias.class);
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
