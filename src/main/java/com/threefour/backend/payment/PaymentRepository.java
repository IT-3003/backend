package com.threefour.backend.payment;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // Spring Boot automatically implements basic database methods here!
    java.util.Optional<Payment> findByTransaction(String transaction);
}
