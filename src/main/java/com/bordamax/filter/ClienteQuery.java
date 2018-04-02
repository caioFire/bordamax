package com.bordamax.filter;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.QCliente;
//import com.mysema.query.BooleanBuilder;
//import com.mysema.query.types.Predicate;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;

/**
 * Created by fire on 28/03/18.
 */
public class ClienteQuery {

    public static Predicate whereByCriterioCliente(FiltroDto filtros) {

        BooleanBuilder builder = new BooleanBuilder();
        QCliente cliente = QCliente.cliente;

        if (filtros.getNome() != null) {
            builder.and(cliente.nome.containsIgnoreCase(filtros.getNome()));
        }
        if (filtros.getStatus() != null) {
            builder.and(cliente.status.eq(filtros.getStatus()));
        }

        return builder;
    }
}
