package com.dunay.test.TestShop.servises;

import com.dunay.test.TestShop.DTO.PersonDetails;
import com.dunay.test.TestShop.models.Person;
import com.dunay.test.TestShop.models.TestOrder;
import com.dunay.test.TestShop.repositories.TestOrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class TestOrdersService {
    private final TestOrdersRepository repository;

    public List<TestOrder> getTestOrdersList() {
        return repository.findAll();
    }

    public List<TestOrder> getTestOrdersListByPersonId(int id) {
        return repository.findAllByPersonId(id);
    }


    public TestOrder getTestOrderById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public void saveTestOrder(TestOrder testOrder, Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person person = personDetails.getPerson();
        testOrder.setPerson(person);
        repository.save(testOrder);
    }


    @Transactional
    public void deleteTestOrder(TestOrder testOrder) {
        repository.delete(testOrder);
    }

}
