package com.ics.oauth2.service.impl;

import com.ics.oauth2.models.ReservedScope;
import com.ics.oauth2.repository.ReservedScopeRepository;
import com.ics.oauth2.service.ReservedScopeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class DefaultReservedScopeServiceImpl implements ReservedScopeService {

    private final ReservedScopeRepository reservedScopeRepository;

    public DefaultReservedScopeServiceImpl(ReservedScopeRepository reservedScopeRepository) {
        this.reservedScopeRepository = reservedScopeRepository;
    }

    @Override
    public ReservedScope saveNew(ReservedScope reservedScope) {
        ReservedScope scope = getById(reservedScope.getId());
        return reservedScopeRepository.save(scope);
    }

    @Override
    public void remove(ReservedScope reservedScope) {
        reservedScopeRepository.delete(getById(reservedScope.getId()));
    }

    @Override
    public ReservedScope getById(Long id) throws IllegalArgumentException{
        if(id>0) {
            return reservedScopeRepository.findById(id)
                    .orElseThrow(
                            () -> new IllegalStateException("Scope doesn't exist with ID = " + id)
                    );
        }
        throw new IllegalArgumentException("ID must be either valid or greater than zero");
    }

    @Override
    public Collection<ReservedScope> getAll() {
        Collection<ReservedScope> reservedScopes = new ArrayList<>();
        reservedScopeRepository.findAll().forEach(reservedScopes::add);
        return reservedScopes;
    }

    @Override
    public boolean isReservedScope(String scope) {
        return reservedScopeRepository.findByScope(scope).isPresent();
    }
}
