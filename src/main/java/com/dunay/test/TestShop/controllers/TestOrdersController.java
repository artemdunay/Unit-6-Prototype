package com.dunay.test.TestShop.controllers;

import com.dunay.test.TestShop.models.TestOrder;
import com.dunay.test.TestShop.servises.AccountProcessingService;
import com.dunay.test.TestShop.servises.TestOrdersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class TestOrdersController {
    private final TestOrdersService testOrdersService;
    private final AccountProcessingService accountProcessingService;

    @GetMapping("/{id}")
    public String getTestOrderInfo(@PathVariable int id, Model model, Authentication authentication) throws AccessDeniedException {
        if (!accountProcessingService.isAccessible(id, authentication)) {
            throw new AccessDeniedException("Access Denied!");
        }
        accountProcessingService.fillModelWithOrderAndOwnerDetails(model, id);
        return "testOrder/show";
    }

    @GetMapping("/new")
    public String getTestOrderCreateForm(@ModelAttribute("testOrder") TestOrder testOrder) {
        return "testOrder/create";
    }

    @PostMapping()
    public String createTestOrder(@Valid @ModelAttribute("testOrder") TestOrder testOrder,
                                  BindingResult bindingResult,
                                  Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "testOrder/create";
        }
        testOrdersService.saveTestOrder(testOrder, authentication);
        return "redirect:/account/info";
    }

    @GetMapping("/{id}/edit")
    public String getTestOrderEditForm
            (@PathVariable int id, Model model, Authentication authentication)
            throws AccessDeniedException {
        if (!accountProcessingService.isAccessible(id, authentication)) {
            throw new AccessDeniedException("Access Denied!");
        }
        model.addAttribute("testOrder", testOrdersService.getTestOrderById(id));
        return "testOrder/edit";
    }

    @PatchMapping("/{id}")
    public String editTestOrder(@PathVariable String id,
                                @Valid @ModelAttribute("testOrder") TestOrder testOrder,
                                BindingResult bindingResult,
                                Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "testOrder/edit";
        }
        testOrdersService.saveTestOrder(testOrder, authentication);
        return "redirect:/order/" + id;
    }

    @DeleteMapping("/{id}/delete")
    public String deleteTestOrder(@PathVariable int id) {
        testOrdersService.deleteTestOrder(testOrdersService.getTestOrderById(id));
        return "redirect:/account/info";
    }
}
