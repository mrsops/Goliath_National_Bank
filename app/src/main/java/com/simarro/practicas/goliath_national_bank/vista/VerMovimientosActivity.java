package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.fragments.Activity_Fragment_Movimientos;
import com.simarro.practicas.goliath_national_bank.fragments.Dialogo;
import com.simarro.practicas.goliath_national_bank.fragments.MovimientosListener;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

public class VerMovimientosActivity extends AppCompatActivity implements MovimientosListener {
    private Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_movimientos);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        Activity_Fragment_Movimientos fragMovs = (Activity_Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FragmentoMovimientos);
        if( fragMovs == null){
            Toast.makeText(this, "No se ha retornado nada, el fragment es null", Toast.LENGTH_SHORT).show();
        }else{
            fragMovs.setMovimientosListener(this);
        }

        Cuenta c = (Cuenta) getIntent().getSerializableExtra("Cuenta");
        
        mostrarMovimientos(c);

    }

    public void mostrarMovimientos(Cuenta c){
        Activity_Fragment_Movimientos fragMovs = (Activity_Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FragmentoMovimientos);
        if (fragMovs==null){
            Toast.makeText(this, "Esto esta a null", Toast.LENGTH_SHORT).show();
        }
        fragMovs.mostrarMovimientos(c);

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

    @Override
    public void onBackPressed() {
        Intent posGlobal = new Intent(this, PosGlobalActivity.class);
        posGlobal.putExtra("Cliente", cliente);
        startActivity(posGlobal);
        finish();
    }
}
