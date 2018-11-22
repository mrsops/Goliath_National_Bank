package com.simarro.practicas.goliath_national_bank.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.adapters.AdaptadorCuentas;
import com.simarro.practicas.goliath_national_bank.adapters.AdaptadorMovimientos;
import com.simarro.practicas.goliath_national_bank.bd.MiBancoOperacional;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

import java.util.ArrayList;

public class Activity_Fragment_Movimientos extends Fragment {
    private ListView listado;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_cuentas, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        listado = (ListView) getView().findViewById(R.id.ListMovimientos);
        Cuenta cuenta = (Cuenta) this.getActivity().getIntent().getSerializableExtra("Cuenta");
        //Cliente cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        MiBancoOperacional bancoOperacional = MiBancoOperacional.getInstance(this.getContext());
        ArrayList<Movimiento> movimientosCuenta = cuenta.getListaMovimientos();
        listado.setAdapter(new AdaptadorMovimientos(this ,movimientosCuenta ));

        /*
        listado = (ListView) getView().findViewById(R.id.ListCuentas);
        Cliente cliente = (Cliente) this.getActivity().getIntent().getSerializableExtra("Cliente");
        //Cliente cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        MiBancoOperacional bancoOperacional = MiBancoOperacional.getInstance(this.getContext());
        ArrayList<Movimiento> movimientos = bancoOperacional.getCuentas(cliente);
        listado.setAdapter(new AdaptadorMovimientos(this,  ));
        */
    }
}
