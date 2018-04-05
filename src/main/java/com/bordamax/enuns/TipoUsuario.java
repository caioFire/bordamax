package com.bordamax.enuns;

/**
 * Created by fire on 05/04/18.
 */
public enum TipoUsuario {

    ADMIN(0),
    MOBILE(1),
    PADRAO(2);

    Integer tipoUsuario;


    TipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


}
