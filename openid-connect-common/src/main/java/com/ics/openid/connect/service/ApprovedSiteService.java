package com.ics.openid.connect.service;

import com.ics.openid.connect.models.ApprovedSite;

import java.util.Collection;

public interface ApprovedSiteService {

    ApprovedSite saveNew(ApprovedSite approvedSite);

    void remove(ApprovedSite approvedSite);

    ApprovedSite getById(Long id);

    Collection<ApprovedSite> getAll();

    boolean isApproved(String uri);

}
