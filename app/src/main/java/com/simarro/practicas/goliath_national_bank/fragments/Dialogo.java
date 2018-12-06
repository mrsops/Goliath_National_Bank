package com.simarro.practicas.goliath_national_bank.fragments;

import android.app.AlertDialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.simarro.practicas.goliath_bank.R;
import com.simarro.practicas.goliath_national_bank.pojo.Movimiento;

public class Dialogo extends DialogFragment {
    TextView importe;
    TextView fecha;
    TextView destino;
    TextView origen;
    TextView descripcion;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view =getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_movimientos, null);
        Movimiento movimiento = (Movimiento) getArguments().getSerializable("obj");

        builder .setView(view).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        importe=(TextView) view.findViewById(R.id.DialogImporte);
        descripcion= (TextView) view.findViewById(R.id.DialogDescripcion);
        fecha= (TextView) view.findViewById(R.id.DialogFecha);
        origen=(TextView) view.findViewById(R.id.DialogOrigen);
        destino=(TextView) view.findViewById(R.id.DialogDestino);

        importe.setText("Importe: "+movimiento.getImporte());
        fecha.setText("Fecha: "+movimiento.getFechaOperacion().toString());
        origen.setText("Origen: "+movimiento.getCuentaOrigen().getNumeroCuenta());
        destino.setText("Destino: "+movimiento.getCuentaDestino().getNumeroCuenta());
        descripcion.setText("Descripcion: "+movimiento.getDescripcion());


        return builder.create();
    }


    public static Dialogo newInstance(Movimiento mov) {

        Dialogo f = new Dialogo();
        Bundle args = new Bundle();
        args.putSerializable("obj",mov);
        f.setArguments(args);

        return f;
    }
}
