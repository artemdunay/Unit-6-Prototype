package com.dunay.test.TestShop.servises;

import com.dunay.test.TestShop.models.TestOrder;
import com.dunay.test.TestShop.repositories.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class TestService {
    private TestRepository repository;

    public List<TestOrder> getTestOrdersList() {
        return repository.findAll();
    }

    public TestOrder getTestOrderById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public void saveTestOrder(TestOrder testOrder) {
        repository.save(testOrder);
    }

    @Transactional
    public void deleteTestOrder(int id) {
        repository.deleteById(id);
    }

}
