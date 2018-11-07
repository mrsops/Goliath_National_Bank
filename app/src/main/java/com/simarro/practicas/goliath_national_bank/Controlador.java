package com.simarro.practicas.goliath_national_bank;

public class Controlador {
    private static Cuenta cuentaActiva;
    private static Banco banco;

    public static boolean accederCuenta(Cuenta c){
        cuentaActiva = c;
        return false;
    }

    public static void asignarBanco(Banco b){
        banco = b;
    }

    public static Banco getBanco(){
        return banco;
    }
}
