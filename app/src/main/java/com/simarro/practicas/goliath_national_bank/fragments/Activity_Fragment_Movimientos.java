package com.simarro.practicas.goliath_national_bank.fragments;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.adapters.AdaptadorCuentas;
import com.simarro.practicas.goliath_national_bank.adapters.AdaptadorMovimientos;
import com.simarro.practicas.goliath_national_bank.bd.MiBancoOperacional;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

import java.util.ArrayList;

public class Activity_Fragment_Movimientos extends Fragment{
    private MovimientosListener listener;
    private ListView listado;
    private MiBancoOperacional bancoOperacional;
    private ArrayList<Movimiento> movimientosCuenta;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_movimientos, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Cliente cliente = (Cliente) getIntent().getSerializableExtra("Cliente");


        /*
        listado = (ListView) getView().findViewById(R.id.ListCuentas);
        Cliente cliente = (Cliente) this.getActivity().getIntent().getSerializableExtra("Cliente");
        //Cliente cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        MiBancoOperacional bancoOperacional = MiBancoOperacional.getInstance(this.getContext());
        ArrayList<Movimiento> movimientos = bancoOperacional.getCuentas(cliente);
        listado.setAdapter(new AdaptadorMovimientos(this,  ));
        */
    }

    public void mostrarMovimientos(Cuenta c){
        listado = (ListView) getView().findViewById(R.id.ListMovimientos);
        this.bancoOperacional = MiBancoOperacional.getInstance(this.getContext());
        movimientosCuenta= this.bancoOperacional.getMovimientos(c);
        listado.setAdapter(new AdaptadorMovimientos(this, movimientosCuenta ));


        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener!=null){
                    listener.onMovimientoSeleccionado(
                            (Movimiento) listado.getAdapter().getItem(position)
                    );
                }
            }
        });
    }

    public void setMovimientosListener(MovimientosListener listener){
        this.listener = listener;
    }

}
