package com.threefour.backend.ruchitha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuchithaRepository extends JpaRepository<Ruchitha, Long> {
    // Spring Boot automatically implements basic database methods here!
}