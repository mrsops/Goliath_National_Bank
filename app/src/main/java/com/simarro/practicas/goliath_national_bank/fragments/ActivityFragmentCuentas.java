package com.simarro.practicas.goliath_national_bank.fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.adapters.AdaptadorCuentas;
import com.simarro.practicas.goliath_national_bank.adapters.AdaptadorCustomizado;
import com.simarro.practicas.goliath_national_bank.bd.MiBancoOperacional;
import com.simarro.practicas.goliath_national_bank.pojo.Cliente;
import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;

import java.util.ArrayList;

public class ActivityFragmentCuentas extends Fragment{
    private ListView listado;
    private CuentasListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_cuentas, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listado = (ListView) getView().findViewById(R.id.ListCuentas);
        Cliente cliente = (Cliente) this.getActivity().getIntent().getSerializableExtra("Cliente");
        //Cliente cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        MiBancoOperacional bancoOperacional = MiBancoOperacional.getInstance(this.getContext());
        ArrayList<Cuenta> cuentas = bancoOperacional.getCuentas(cliente);
        listado.setAdapter(new AdaptadorCuentas(this ,cuentas ));
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listener!=null){
                    listener.onCuentaSeleccionada(
                            (Cuenta)listado.getAdapter().getItem(position)
                    );
                }
            }
        });
    }


    public void setCuentasListener(CuentasListener listener){
        this.listener = listener;
    }
}
