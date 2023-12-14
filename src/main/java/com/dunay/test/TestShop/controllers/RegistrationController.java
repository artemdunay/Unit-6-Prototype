package com.dunay.test.TestShop.controllers;

import com.dunay.test.TestShop.models.Person;
import com.dunay.test.TestShop.servises.PersonDetailsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class RegistrationController {

    private final PersonDetailsService personDetailsService;

    @GetMapping("/registration")
    public String getRegistrationForm(@ModelAttribute("person") Person person) {
        return "security/registration";
    }

    @PostMapping("/registration")
    public String createRegistration(@Valid @ModelAttribute("person") Person person,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "security/registration";
        }
        try {
            personDetailsService.registerPerson(person);
        } catch (Exception e) {
            return "redirect:/registration?error";
        }
        return "redirect:/login";
    }
}
