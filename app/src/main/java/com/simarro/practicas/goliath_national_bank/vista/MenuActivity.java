package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.bd.MiBancoOperacional;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Cliente cliente;
    private MiBancoOperacional bancoOperacional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        bancoOperacional = MiBancoOperacional.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        Button clave = findViewById(R.id.change_pass);
        Button operaciones = findViewById(R.id.operations);
        Button posGlobal = findViewById(R.id.pos_global);
        Button cerrarSesion = findViewById(R.id.close_sesion);
        Button cajeros = findViewById(R.id.cajeros);
        Button ingresos = findViewById(R.id.income);

        if (clave.getId()== v.getId()){
            Intent changePass = new Intent(this, CambioPass.class);
            changePass.putExtra("Cliente", cliente);
            startActivity(changePass);
            finish();

        }else if(posGlobal.getId()==v.getId()){
            Intent posGlobalActivity = new Intent(this, PosGlobalActivity.class);
            posGlobalActivity.putExtra("Cliente", cliente);
            startActivity(posGlobalActivity);
            finish();
        }else if(cerrarSesion.getId()==v.getId()){
            Intent menu = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(menu);
            finish();

        }else if(cajeros.getId()==v.getId()){
            if(esAdmin(this.cliente)){
                Intent intent = new Intent(this, CajerosActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Solo los administradores pueden acceder a este apartado", Toast.LENGTH_LONG).show();
            }


        }else if(ingresos.getId()==v.getId()){
            Toast.makeText(MenuActivity.this, cliente.getNombre()+" "+this.cliente.getClaveSeguridad(), Toast.LENGTH_LONG).show();

        }else if(operaciones.getId()==v.getId()){
            startActivity(new Intent(MenuActivity.this, Transferencias.class));
            finish();
        }else{

        }

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
                Intent settings = new Intent(this, PreferenciasActivity.class);
                startActivity(settings);
                return true;
                
            case R.id.change_pass_toolbar:
                Intent changePass = new Intent(this, CambioPass.class);
                changePass.putExtra("Cliente", cliente);
                startActivity(changePass);
                finish();
                return true;
                

            case R.id.cajeros_toolbar:
                if(esAdmin(this.cliente)){
                    Intent intent = new Intent(this, CajerosActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Solo los administradores pueden acceder a este apartado", Toast.LENGTH_LONG).show();
                }
                return true;
                
            case R.id.operations_toolbar:
                Intent transf = new Intent(this, Transferencias.class);
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
            case R.id.close_sesion_toolbar:
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                finish();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
                
                
        }
    }
    public boolean esAdmin(Cliente cliente) {
        return cliente.getNif().equals("11111111A");
    }
}
