package com.tms.service;

import com.tms.model.Security;

public interface SecurityService {
    Security getSecurityByUserId(Long userId);

    void createSecurity(Security security);

    void updateSecurity(Security security);
}

