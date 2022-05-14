package com.ics.oauth2.service;

import com.ics.oauth2.models.ReservedScope;

import java.util.Collection;

public interface ReservedScopeService {

    ReservedScope saveNew(ReservedScope reservedScope);

    void remove(ReservedScope reservedScope);

    ReservedScope getById(Long id);

    Collection<ReservedScope> getAll();

    boolean isReservedScope(String scope);

}
