package com.bordamax.repository;

import com.bordamax.entity.Amostra;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fire on 13/03/18.
 */

@Repository
public interface AmostraRepository extends PagingAndSortingRepository<Amostra, Long>, QueryDslPredicateExecutor<Amostra> {

    List<Amostra> findAllByStatusAndPortfolio(boolean status, boolean portfolio);

    Amostra findFirstByCodigo(String codigo);

    Amostra findFirstByLocalizacao_Id(Long id);

    Amostra findFirstByCliente_Id(Long id);

    @Query(value = "SELECT *\n" +
            "FROM public.amostra a\n" +
            "WHERE a.codigo LIKE '%' ||?1|| '%'" +
            "AND a.status = TRUE \n" +
            "ORDER BY a.codigo ASC \n" +
            "LIMIT 10", nativeQuery = true)
    List<Amostra> findTopByAPPPesquisa(String codigo);

}
