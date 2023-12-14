package com.dunay.test.TestShop.controllers;

import com.dunay.test.TestShop.servises.TestOrdersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final TestOrdersService testOrdersService;

    @GetMapping("/list")
    public String getTestOrdersList(Model model) {
        model.addAttribute("orders", testOrdersService.getTestOrdersList());
        return "testOrder/index";
    }
}
