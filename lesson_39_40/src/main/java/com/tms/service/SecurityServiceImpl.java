package com.tms.service;

import com.tms.model.Security;
import com.tms.model.dto.RegistrationDto;
import com.tms.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    public final SecurityRepository securityRepository;

    @Autowired
    public SecurityServiceImpl(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    @Override
    public Optional<Security> updateSecurity(Security security) throws SQLException {
        Boolean result = securityRepository.updateSecurity(security);
        if (result) {
            return getSecurityById(security.getId());
        }
        return Optional.empty();
    }

    @Override
    public void registration(RegistrationDto requestDto) throws SQLException {
        securityRepository.registration(requestDto);
    }

    @Override
    public Optional<Security> getSecurityById(Long id) {
        return securityRepository.getSecurityById(id);
    }

    @Override
    public Optional<Security> deleteSecurity(Long id) {
        Boolean result = securityRepository.deleteSecurity(id);
        if (result) {
            return getSecurityById(id);
        }
        return Optional.empty();
    }
}
