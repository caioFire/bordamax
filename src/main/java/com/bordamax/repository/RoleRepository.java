package com.bordamax.repository;

import com.bordamax.entity.Amostra;
import com.bordamax.entity.Localizacao;
import com.bordamax.entity.Roles;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fire on 13/03/18.
 */

@Repository
public interface RoleRepository extends CrudRepository<Roles, Long> {

    Roles findFirstByRole(String role);

}
