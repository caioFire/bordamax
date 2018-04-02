package com.bordamax.repository;

import com.bordamax.entity.Localizacao;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

/**
 * Created by fire on 13/03/18.
 */

@Repository
public interface LocalizacaoRepository extends PagingAndSortingRepository<Localizacao, Long> , QueryDslPredicateExecutor<Localizacao> {

        List<Localizacao> findAllByStatus(boolean status);

        Localizacao findFirstByNome(String nome);

}