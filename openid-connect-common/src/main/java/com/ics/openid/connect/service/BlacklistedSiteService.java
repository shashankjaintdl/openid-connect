package com.ics.openid.connect.service;

import com.ics.openid.connect.models.BlacklistedSite;

import java.util.Collection;

public interface BlacklistedSiteService {

    BlacklistedSite saveNew(BlacklistedSite blacklistedSite);

    void remove(BlacklistedSite blacklistedSite);

    BlacklistedSite getById(Long id);

    Collection<BlacklistedSite> getAll();

    boolean isBlacklisted(String uri);

    BlacklistedSite update(BlacklistedSite oldBlacklistedSite, BlacklistedSite newBlacklistedSite);
}
