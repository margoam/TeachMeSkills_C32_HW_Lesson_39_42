package com.tms.controller;

import com.tms.model.Security;
import com.tms.model.dto.RegistrationDto;
import com.tms.service.SecurityService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;


@Controller
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/edit/{id}")
    public String getSecurityById(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        Optional<Security> security = securityService.getSecurityById(id);
        if (security.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "Security not found: id=" + id);
            return "innerError";
        }
        model.addAttribute("security", security.get());
        return "editSecurity";
    }

    // Create (with user creation)
    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid RegistrationDto requestDto,
                               BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "registration";
        }
        securityService.registration(requestDto);
        return "redirect:/user/all-users";
    }

    // Update
    @PostMapping("/update")
    public String updateSecurity(@ModelAttribute("security") Security security,
                                 Model model,
                                 HttpServletResponse response) {
        Optional<Security> securityUpdated = securityService.updateSecurity(security);
        if (securityUpdated.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "Security not found.");
            return "innerError";
        }
        return "redirect:/user/all-users";
    }

    // Delete
    @PostMapping("/delete")
    public String deleteSecurity(@RequestParam("id") Long id,
                                 Model model,
                                 HttpServletResponse response) {
        Optional<Security> securityDeleted = securityService.deleteSecurity(id);
        if (securityDeleted.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Security is not deleted.");
            return "innerError";
        }
        return "redirect:/user/all-users";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteSecurityPage(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        Optional<Security> security = securityService.getSecurityById(id);
        if (security.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "Security not found: id=" + id);
            return "innerError";
        }
        model.addAttribute("security", security.get());
        return "deleteSecurity";
    }
}