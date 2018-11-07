package com.simarro.practicas.goliath_national_bank;

import java.util.ArrayList;

public class Banco {
    private ArrayList<Cuenta> cuentas;

    public Banco() {
        this.cuentas = new ArrayList<>();
    }


    public Cuenta comprobarAcceso(String usuario, String contraseña){
        for (int i = 0; i <this.cuentas.size() ; i++) {
            if(this.cuentas.get(i).verifyUserAndPass(usuario, contraseña) ){
                return cuentas.get(i);
            }
        }
        return null;
    }

    public void add(Cuenta c){
        this.cuentas.add(c);
    }
}
