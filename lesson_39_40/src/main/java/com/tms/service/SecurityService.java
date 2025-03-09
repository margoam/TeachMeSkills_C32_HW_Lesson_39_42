package com.tms.service;

import com.tms.model.Security;
import com.tms.model.dto.RegistrationRequestDto;

public interface SecurityService {
    Security getSecurityByUserId(Long userId);

    void createSecurity(Security security);

    Boolean registration(RegistrationRequestDto requestDto);
}

