package com.simarro.practicas.goliath_national_bank;

import org.apache.commons.codec.digest.DigestUtils;

public class Cuenta {
    private String usuario;
    private String contraseña;


    public Cuenta(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Cuenta() {
    }

    public boolean verifyUserAndPass(String usuario, String contraseña){
        if(this.usuario.equals(usuario) && this.contraseña.equals(contraseña)){
            return true;
        }else{
            return false;
        }

    }
}
