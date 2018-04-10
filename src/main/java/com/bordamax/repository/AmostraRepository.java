package com.bordamax.repository;

import com.bordamax.entity.Amostra;
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

    List<Amostra> findAllByCodigoAndStatus(String codigo, boolean status);

    Amostra findFirstByCodigo(String codigo);

    Amostra findFirstByLocalizacao_Id(Long id);

    Amostra findFirstByCliente_Id(Long id);

}
