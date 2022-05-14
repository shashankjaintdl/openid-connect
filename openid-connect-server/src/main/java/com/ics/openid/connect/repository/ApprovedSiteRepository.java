package com.ics.openid.connect.repository;

import com.ics.openid.connect.models.ApprovedSite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovedSiteRepository extends CrudRepository<ApprovedSite, Long> {
}
