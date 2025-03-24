package com.tms.controller;

import com.tms.model.Security;
import com.tms.model.dto.RegistrationDto;
import com.tms.service.SecurityService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("api/security")
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @ApiResponses(value = {
            @ApiResponse(description = "When dto created.", responseCode = "201"),
            @ApiResponse(description = "When something wrong.", responseCode = "409")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Security> getSecurityById(@PathVariable("id") Long id) {
        Optional<Security> security = securityService.getSecurityById(id);
        if (security.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(security.get(), HttpStatus.OK);
    }

    // Create (with user creation)
    @PostMapping("/registration")
    public ResponseEntity<RegistrationDto> registration(@RequestBody RegistrationDto requestDto,
                                                        BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        securityService.registration(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/update")
    public ResponseEntity<Security> updateSecurity(@RequestBody Security security) {
        Optional<Security> securityUpdated = securityService.updateSecurity(security);
        if (securityUpdated.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(securityUpdated.get(), HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSecurity(@PathVariable("id") @Parameter(description = "Security id") Long id) {
        Optional<Security> securityDeleted = securityService.deleteSecurity(id);
        if (securityDeleted.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}