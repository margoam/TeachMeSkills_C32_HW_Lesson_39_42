package com.tms.controller;

import com.tms.model.User;
import com.tms.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/create")
    public String getUserCreatePage() {
        return "createUser";
    }

    @GetMapping
    public String getUserUpdatePage(@RequestParam("userId") Long userId, Model model, HttpServletResponse response) {
        Optional<User> user = userServiceImpl.getUserById(userId);
        if (!user.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            model.addAttribute("message", "User not found: id=" + userId);
            return "innerError";
        }
        model.addAttribute("user", user.get());
        return "edit";
    }

    //Create
    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, HttpServletResponse response, Model model) {
        Optional<User> createdUser = userServiceImpl.createUser(user);
        if (!createdUser.isPresent()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not created");
            return "innerError";
        }
        model.addAttribute("user", createdUser.get());
        return "user";
    }

    //Read
    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        Optional<User> user = userServiceImpl.getUserById(id);
        if (!user.isPresent()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
            model.addAttribute("message", "User not found: id=" + id);
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK); //200
        model.addAttribute("user", user.get());
        return "user";
    }

    //Update
    @PostMapping
    public String updateUser(@ModelAttribute("user") User user, Model model, HttpServletResponse response) {
        Optional<User> userUpdated = userServiceImpl.updateUser(user);
        if (!userUpdated.isPresent()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not updated.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", userUpdated.get());
        return "user";
    }

    //Delete
    @PostMapping("/delete/{id}")
    public String deleteUser(@RequestParam("id") Long userId, Model model, HttpServletResponse response) {
        Optional<User> userDeleted = userServiceImpl.deleteUser(userId);
        if (!userDeleted.isPresent()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User is not deleted.");
            return "innerError";
        }
        response.setStatus(HttpServletResponse.SC_OK);
        model.addAttribute("user", userDeleted.get());
        return "user";
    }
}
