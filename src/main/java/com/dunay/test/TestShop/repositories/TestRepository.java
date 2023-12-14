package com.dunay.test.TestShop.repositories;

import com.dunay.test.TestShop.models.TestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestOrder,Integer> {
}