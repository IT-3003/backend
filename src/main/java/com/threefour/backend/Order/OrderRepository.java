package com.threefour.backend.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ruchitha, Long> {
    // Spring Boot automatically implements basic database methods here!
}