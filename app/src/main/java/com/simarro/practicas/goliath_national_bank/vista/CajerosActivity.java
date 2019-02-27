package com.simarro.practicas.goliath_national_bank.vista;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.adapters.AdaptadorCajeros;
import com.simarro.practicas.goliath_national_bank.dao.CajeroDAO;
import com.simarro.practicas.goliath_national_bank.pojo.Constantes;

public class CajerosActivity extends AppCompatActivity {

    private ListView list;
    private AdaptadorCajeros adapter;
    private CajeroDAO cajeroDAO;
    private Cursor cursor;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajeros);
        list = findViewById(R.id.listCajeros);
        cajeroDAO = new CajeroDAO(this);
        cajeroDAO.abrir();
        cursor = cajeroDAO.getCursor();
        startManagingCursor(cursor);
        adapter = new AdaptadorCajeros(this, cursor);
        list.setAdapter(adapter);

        //toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CajerosActivity.this, GestionCajerosActivity.class);
                i.putExtra(Constantes.C_MODO, Constantes.C_VISUALIZAR);
                i.putExtra(Constantes.FIELD_CAJEROS_ID, id);
                startActivityForResult(i, Constantes.C_VISUALIZAR);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_caj_crear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_crear:
                i = new Intent(this, GestionCajerosActivity.class);
                i.putExtra(Constantes.C_MODO, Constantes.C_CREAR);
                startActivityForResult(i, Constantes.C_CREAR);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case Constantes.C_CREAR:
                if (resultCode == RESULT_OK)
                    recargarLista();
            case Constantes.C_VISUALIZAR:
                if (resultCode == RESULT_OK)
                    recargarLista();
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void recargarLista() {
        CajeroDAO cajeroDAO = new CajeroDAO(getBaseContext());
        cajeroDAO.abrir();
        AdaptadorCajeros adapter = new AdaptadorCajeros(this, cajeroDAO.getCursor());
        list.setAdapter(adapter);
    }
}
