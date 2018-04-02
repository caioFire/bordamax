package com.bordamax.filter;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.QAmostra;
//import com.mysema.query.BooleanBuilder;
//import com.mysema.query.types.Predicate;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;

/**
 * Created by fire on 28/03/18.
 */
public class AmostraQuery {

    public static Predicate whereByCriterioAmostra(FiltroDto filtros) {

        BooleanBuilder builder = new BooleanBuilder();
        QAmostra amostra = QAmostra.amostra;

        if (filtros.getCodigo() != null) {
            builder.and(amostra.codigo.containsIgnoreCase(filtros.getCodigo()));
        }
        if (filtros.getStatus() != null) {
            builder.and(amostra.status.eq(filtros.getStatus()));
        }
        if (filtros.getPortfolio() != null) {
            builder.and(amostra.portfolio.eq(filtros.getPortfolio()));
        }
        if (filtros.getCliente() != null) {
            builder.and(amostra.cliente.nome.containsIgnoreCase(filtros.getCliente()));
        }
        if (filtros.getLocalizacao() != null) {
            builder.and(amostra.localizacao.nome.containsIgnoreCase(filtros.getLocalizacao()));
        }
        return builder;
    }
}
