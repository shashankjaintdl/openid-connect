package com.ics.oauth2.repository;

import com.ics.oauth2.models.SecurityAuthenticationHolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityAuthenticationHolderRepository extends JpaRepository<SecurityAuthenticationHolderEntity, Long> {

}
