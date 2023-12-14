package com.dunay.test.TestShop.repositories;

import com.dunay.test.TestShop.models.TestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestOrdersRepository extends JpaRepository<TestOrder, Integer> {
    List<TestOrder> findAllByPersonId(int id);
}