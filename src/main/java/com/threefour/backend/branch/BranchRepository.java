package com.threefour.backend.branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    List<Branch> findByIsActiveTrue();
    // Spring Boot automatically implements basic database methods here!
}

