package com.ics.oauth2.repository;

import com.ics.oauth2.models.ReservedScope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservedScopeRepository extends CrudRepository<ReservedScope,Long> {

    Optional<ReservedScope> findByScope(String scope);

}
