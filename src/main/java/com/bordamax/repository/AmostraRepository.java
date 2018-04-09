package com.bordamax.repository;

import com.bordamax.entity.Amostra;
import com.bordamax.entity.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
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

}
