package com.ics.openid.connect.service.impl;

import com.ics.openid.connect.models.ApprovedSite;
import com.ics.openid.connect.service.ApprovedSiteService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DefaultApprovedSiteServiceImpl implements ApprovedSiteService {

    @Override
    public ApprovedSite saveNew(ApprovedSite approvedSite) {
        return null;
    }

    @Override
    public void remove(ApprovedSite approvedSite) {

    }

    @Override
    public ApprovedSite getById(Long id) {
        return null;
    }

    @Override
    public Collection<ApprovedSite> getAll() {
        return null;
    }

    @Override
    public boolean isApproved(String uri) {
        return false;
    }
}
