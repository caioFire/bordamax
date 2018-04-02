package com.bordamax.repository;

import com.bordamax.entity.Localizacao;
import com.bordamax.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fire on 13/03/18.
 */


@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>, QueryDslPredicateExecutor<Localizacao> {

    List<Usuario> findAllByStatus(boolean status);

    Usuario findFirstByNome(String nome);

    Usuario findFirstByIdentificacao(String identificacao);

}
