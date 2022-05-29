package com.ics.oauth2.repository;

import com.ics.oauth2.models.OAuth2RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2RefreshTokenRepository extends CrudRepository<OAuth2RefreshTokenEntity, Long> {
}
