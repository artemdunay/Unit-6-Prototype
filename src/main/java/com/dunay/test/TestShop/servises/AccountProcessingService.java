package com.dunay.test.TestShop.servises;

import com.dunay.test.TestShop.DTO.PersonDetails;
import com.dunay.test.TestShop.models.TestOrder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AccountProcessingService {

    private final PersonDetailsService personDetailsService;

    private final TestOrdersService testOrdersService;

    public void fillModelWithPersonDetailsAndOrders(Model model, Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        PersonDetails consistentPersonDetails = personDetailsService.getPersonDetailsById(personDetails.getId());
        List<TestOrder> testOrders = testOrdersService.getTestOrdersListByPersonId(personDetails.getId());
        model.addAttribute("testOrders", testOrders);
        model.addAttribute("personDetails", consistentPersonDetails);
    }

    public void fillModelWithOrderAndOwnerDetails(Model model, int id) {
        TestOrder testOrder = testOrdersService.getTestOrderById(id);
        PersonDetails consistentPersonDetails = new PersonDetails(testOrder.getPerson());
        model.addAttribute("testOrder", testOrder);
        model.addAttribute("personDetails", consistentPersonDetails);
    }

    public void fillModelWithPersonDetails(Model model, Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        PersonDetails consistentPersonDetails = personDetailsService.getPersonDetailsById(personDetails.getId());
        model.addAttribute("personDetails", consistentPersonDetails);
    }


    public boolean isAccessible(int id, Authentication authentication) {
        if (authentication.getAuthorities().
                contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return true;
        }
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        TestOrder testOrder = testOrdersService.getTestOrderById(id);
        int personId = testOrder.getPerson().getId();
        return personId == personDetails.getId();
    }


}
