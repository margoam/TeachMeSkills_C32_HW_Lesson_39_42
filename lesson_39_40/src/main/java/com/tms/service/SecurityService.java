package com.tms.service;

import com.tms.model.Security;
import com.tms.model.dto.RegistrationDto;
import com.tms.repository.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class SecurityService {

    private final SecurityRepository securityRepository;

    @Autowired
    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public Optional<Security> updateSecurity(Security security)
    {
        Boolean result = securityRepository.updateSecurity(security);
        if (result) {
            return getSecurityById(security.getId());
        }
        return Optional.empty();
    }

    public void registration(RegistrationDto requestDto) throws SQLException {
        securityRepository.registration(requestDto);
    }

    public Optional<Security> getSecurityById(Long id) {
        return securityRepository.getSecurityById(id);
    }

    public Optional<Security> deleteSecurity(Long id) {
        Boolean result = securityRepository.deleteSecurity(id);
        if (result) {
            return getSecurityById(id);
        }
        return Optional.empty();
    }
}