package com.simarro.practicas.goliath_national_bank.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simarro.practicas.goliath_national_bank.pojo.Cuenta;

import java.util.ArrayList;


public class MovimientosFragment extends Fragment {
    private ArrayList<Cuenta> cuentas;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public MovimientosFragment() {
        this.cuentas = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (Cuenta c: cuentas){

        }
    }
}
