package com.tms.controller;

import com.tms.model.User;
import com.tms.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/create")
    public String getUserCreatePage() {
        return "createUser";
    }

    @GetMapping("/edit/{id}")
    public String getUserUpdatePage(@PathVariable("id") Long userId, Model model, HttpServletResponse response) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("message", "User not found: id=" + userId);
            return "innerError";
        }
        model.addAttribute("user", user.get());
        return "editUser";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user, HttpServletResponse response, Model model) {
        Optional<User> createdUser = userService.createUser(user);
        if (createdUser.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not created");
            return "innerError";
        }
        return "redirect:/user/all-users";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, Model model, HttpServletResponse response) {
        Optional<User> userUpdated = userService.updateUser(user);
        if (userUpdated.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User not updated.");
            return "innerError";
        }
        return "redirect:/user/all-users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long userId, Model model, HttpServletResponse response) {
        Optional<User> userDeleted = userService.deleteUser(userId);
        if (userDeleted.isPresent()) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            model.addAttribute("message", "User is not deleted.");
            return "innerError";
        }
        return "redirect:/user/all-users";
    }
}