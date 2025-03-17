package com.tms.service;

import com.tms.model.Security;
import com.tms.model.dto.RegistrationDto;

import java.sql.SQLException;
import java.util.Optional;

public interface SecurityService {

    Optional<Security> updateSecurity(Security security) throws SQLException;

    void registration(RegistrationDto requestDto) throws SQLException;

    Optional<Security> getSecurityById(Long id);

    Optional<Security> deleteSecurity(Long id);
}

