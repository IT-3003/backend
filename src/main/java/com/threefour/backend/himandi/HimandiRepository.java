package com.threefour.backend.himandi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface HimandiRepository extends JpaRepository<Himandi, Long> {
    // Spring Boot automatically implements basic database methods here!
}

