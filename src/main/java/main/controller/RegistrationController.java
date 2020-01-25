package main.controller;

import main.model.Role;
import main.model.User;
import main.model.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(User u, Model model){
        User user = userRepo.findByUsername(u.getUsername());
        if (user != null){
            model.addAttribute("message", "User already exist");
            return "registration";
        }
        u.setActive(true);
        u.setRoles(Collections.singleton(Role.USER));
        userRepo.save(u);
        return "redirect:/login";
    }
}
