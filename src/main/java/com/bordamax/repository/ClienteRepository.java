package com.bordamax.repository;

import com.bordamax.entity.Cliente;
import com.bordamax.entity.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fire on 13/03/18.
 */
@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>, QueryDslPredicateExecutor<Localizacao> {

    List<Cliente> findAllByStatus(boolean status);

    Cliente findFirstByNome(String nome);

}