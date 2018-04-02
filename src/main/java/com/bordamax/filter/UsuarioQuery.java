package com.bordamax.filter;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.QCliente;
import com.bordamax.entity.QUsuario;
//import com.mysema.query.BooleanBuilder;
//import com.mysema.query.types.Predicate;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
/**
 * Created by fire on 28/03/18.
 */
public class UsuarioQuery {

    public static Predicate whereByCriterioUsuario(FiltroDto filtros) {

        BooleanBuilder builder = new BooleanBuilder();
        QUsuario usuario = QUsuario.usuario;

        if (filtros.getNome() != null) {
            builder.and(usuario.nome.containsIgnoreCase(filtros.getNome()));
        }
        if (filtros.getStatus() != null) {
            builder.and(usuario.status.eq(filtros.getStatus()));
        }
        if (filtros.getLogin() != null) {
            builder.and(usuario.identificacao.containsIgnoreCase(filtros.getLogin()));
        }
        return builder;
    }
}
