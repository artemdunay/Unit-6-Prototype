package com.dunay.test.TestShop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainController {

    @GetMapping
    public String welcomePage() {
        return "main/welcomePage";
    }
}
