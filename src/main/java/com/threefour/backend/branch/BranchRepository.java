package com.threefour.backend.branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    // Spring Boot automatically implements basic database methods here!
}

