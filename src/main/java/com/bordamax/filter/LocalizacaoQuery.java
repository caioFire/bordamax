package com.bordamax.filter;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.QLocalizacao;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;




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
