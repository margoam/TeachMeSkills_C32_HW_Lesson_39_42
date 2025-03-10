package com.tms.controller;

import com.tms.model.Security;
import com.tms.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/{userId}")
    public String getSecurityByUserId(@PathVariable("userId") Long userId, Model model) {
        Security security = securityService.getSecurityByUserId(userId);
        model.addAttribute("security", security);
        return "security";
    }

    @PostMapping("/{userId}/update")
    public String updateSecurity(@PathVariable("userId") Long userId, @ModelAttribute Security security) {
        security.setUserId(userId);
        securityService.updateSecurity(security);
        return "redirect:/security/" + userId;
    }
}
