package com.dunay.test.TestShop.controllers;

import com.dunay.test.TestShop.servises.AccountProcessingService;
import com.dunay.test.TestShop.servises.PersonDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class PersonsController {
    private final PersonDetailsService personDetailsService;
    private final AccountProcessingService accountProcessingService;

    @GetMapping("/info")
    public String getPersonsDetails(Model model, Authentication authentication) {
        accountProcessingService.fillModelWithPersonDetailsAndOrders(model, authentication);
        return "person/accountDetails";
    }

    @GetMapping("/edit")
    public String getAccountEditForm(Model model, Authentication authentication) {
        accountProcessingService.fillModelWithPersonDetails(model, authentication);
        return "person/editAccountDetails";
    }

    @PostMapping("/edit")
    public String editAccount(Authentication authentication,
                              @RequestBody MultiValueMap<String, String> formData) {
        try {
            personDetailsService.editPersonDetails(authentication, formData);
        } catch (Exception e) {
            return "redirect:/account/edit?error";
        }
        return "redirect:/account/info";
    }

    @DeleteMapping("/delete")
    public String deleteAccount(Authentication authentication, HttpServletRequest httpServletRequest) throws ServletException {
        personDetailsService.deletePerson(authentication);
        httpServletRequest.logout();
        return "redirect:/";
    }

    @GetMapping("/role")
    public String getAdminRole(Authentication authentication, HttpServletRequest httpServletRequest) throws ServletException {
        personDetailsService.setAdminRole(authentication);
        httpServletRequest.logout();
        return "redirect:/account/info";
    }
}

