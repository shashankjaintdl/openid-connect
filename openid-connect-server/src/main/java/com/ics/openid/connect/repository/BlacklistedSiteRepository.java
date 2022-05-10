package com.ics.openid.connect.repository;

import com.ics.openid.connect.models.BlacklistedSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistedSiteRepository extends JpaRepository<BlacklistedSite, Long> {

}
