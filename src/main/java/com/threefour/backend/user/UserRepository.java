package com.threefour.backend.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Boot automatically implements basic database methods here!
}
