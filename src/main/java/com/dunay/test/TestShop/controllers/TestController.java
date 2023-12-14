package com.dunay.test.TestShop.controllers;

import com.dunay.test.TestShop.models.TestOrder;
import com.dunay.test.TestShop.servises.TestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
    private TestService service;

    @GetMapping
    public String getTestOrdersList(Model model) {
        model.addAttribute("orders", service.getTestOrdersList());
        return "TestOrder/index";
    }

    @GetMapping("/{id}")
    public String getTestOrderInfo(@PathVariable int id, Model model) {
        model.addAttribute("testOrder", service.getTestOrderById(id));
        return "TestOrder/show";
    }

    @GetMapping("/new")
    public String getTestOrderCreateForm(@ModelAttribute("testOrder") TestOrder testOrder) {
        return "TestOrder/create";
    }

    @PostMapping()
    public String createTestOrder(@Valid @ModelAttribute("testOrder") TestOrder testOrder,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "TestOrder/create";
        }
        service.saveTestOrder(testOrder);
        return "redirect:/test";
    }

    @GetMapping("/{id}/edit")
    public String getTestOrderEditForm(@PathVariable int id, Model model) {
        model.addAttribute("testOrder", service.getTestOrderById(id));
        return "TestOrder/edit";
    }

    @PatchMapping("/{id}")
    public String editTestOrder(@PathVariable String id,
                                @Valid @ModelAttribute("testOrder") TestOrder testOrder,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "TestOrder/edit";
        }
        service.saveTestOrder(testOrder);
        return "redirect:/test/" + id;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteTestOrder(@PathVariable int id) {
        service.deleteTestOrder(id);
        return "redirect:/test";
    }
}
