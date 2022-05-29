package com.ics.oauth2.repository;

import com.ics.oauth2.models.OAuth2AccessTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2AccessTokenRepository extends CrudRepository<OAuth2AccessTokenEntity, Long>{
}
