package com.bordamax.filter;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.QLocalizacao;
//import com.mysema.query.BooleanBuilder;
//import com.mysema.query.types.Predicate;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;


/**
 * Created by fire on 28/03/18.
 */
public class LocalizacaoQuery {

    public static Predicate whereByCriterioLocalizacao(FiltroDto filtros) {

        BooleanBuilder builder = new BooleanBuilder();
        QLocalizacao localizacao = QLocalizacao.localizacao;

        if (filtros.getNome() != null) {
            builder.and(localizacao.nome.containsIgnoreCase(filtros.getNome()));
        }
        if (filtros.getStatus() != null) {
            builder.and(localizacao.status.eq(filtros.getStatus()));
        }

        return builder;
    }
}
