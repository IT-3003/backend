package com.threefour.backend.payment;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
// Pass your Entity type (Product) and its ID type (Long) into the generic
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    // Spring Boot automatically implements basic database methods here!
    java.util.Optional<Payment> findByTransaction(String transaction);

    @Transactional
    void deleteByUser_Id(int userId);

    @Transactional
    void deleteByOrder_OrderId(int orderId);

    @Transactional
    void deleteByOrder_Branch_BranchId(int branchId);
}