package com.threefour.backend.thinula;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface ThinulaRepository extends JpaRepository<Thinula, Long> {
    // Spring Boot automatically implements basic database methods here!
}
