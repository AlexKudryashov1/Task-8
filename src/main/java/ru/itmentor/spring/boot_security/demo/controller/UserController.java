package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;


@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping()
    public String show(Model model,@AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user";
    }

}

