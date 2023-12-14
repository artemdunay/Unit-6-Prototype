package com.dunay.test.TestShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String loginPageForm() {
        return "security/login";
    }

    @PostMapping("/loginPost")
    public String loginPagePost() {
        return "redirect:/";
    }
}
