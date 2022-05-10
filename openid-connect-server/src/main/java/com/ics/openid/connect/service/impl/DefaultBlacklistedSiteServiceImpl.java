package com.ics.openid.connect.service.impl;

import com.google.common.base.Strings;
import com.ics.openid.connect.models.BlacklistedSite;
import com.ics.openid.connect.repository.BlacklistedSiteRepository;
import com.ics.openid.connect.service.BlacklistedSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class DefaultBlacklistedSiteServiceImpl implements BlacklistedSiteService {

    private final BlacklistedSiteRepository repository;

    @Autowired
    public DefaultBlacklistedSiteServiceImpl(BlacklistedSiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public BlacklistedSite saveNew(BlacklistedSite blacklistedSite) {
        return repository.save(blacklistedSite);
    }

    @Override
    public void remove(BlacklistedSite blacklistedSite) {
        BlacklistedSite site = getById(blacklistedSite.getId());
        repository.delete(site);
    }

    @Override
    public BlacklistedSite getById(Long id) {
        return repository.findById(id).orElseThrow(()->{
                    throw new IllegalArgumentException();
                });
    }

    @Override
    public Collection<BlacklistedSite> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean isBlacklisted(String uri) {
        if (Strings.isNullOrEmpty(uri)){
            return false;
        }
        Collection<BlacklistedSite> allSites = getAll();
        for(BlacklistedSite b:allSites){
            if (b.getUri().equals(uri)){
                return true;
            }
        }
        return false;
    }

    @Override
    public BlacklistedSite update(BlacklistedSite oldBlacklistedSite, BlacklistedSite newBlacklistedSite) {

        return null;
    }


}
