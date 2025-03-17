package com.tms.controller;

import com.tms.model.Security;
import com.tms.model.dto.RegistrationDto;
import com.tms.service.SecurityServiceImpl;
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

    public SecurityServiceImpl securityService;

    @Autowired
    public SecurityController(SecurityServiceImpl securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    //create (with user creation)
    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid RegistrationDto requestDto,
                               BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "registration";
        }
        securityService.registration(requestDto);
        return "user";
    }

    //update
    @PostMapping
    public String updateSecurity(@ModelAttribute("security") Security security, Model model, HttpServletResponse response) throws SQLException {
        Optional<Security> securityUpdated = securityService.updateSecurity(security);
        if (!securityUpdated.isPresent()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Login and password were not updated.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("security", securityUpdated.get());
        return "user";
    }

    //Delete
    @PostMapping("/delete/{id}")
    public String deleteSecurity(@RequestParam("id") Long id, Model model, HttpServletResponse response) {
        Optional<Security> securityDeleted = securityService.deleteSecurity(id);
        if (!securityDeleted.isPresent()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "Login and password are not deleted.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("security", securityDeleted.get());
        return "user";
    }
}
