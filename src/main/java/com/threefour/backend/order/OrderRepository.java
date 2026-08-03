package com.threefour.backend.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.payment = null WHERE o.payment.paymentId = :paymentId")
    void clearPaymentReference(@Param("paymentId") int paymentId);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.payment = null WHERE o.orderId = :orderId")
    void clearPaymentReferenceForOrder(@Param("orderId") int orderId);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.payment = null WHERE o.user.id = :userId")
    void clearPaymentReferencesByUserId(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("UPDATE Order o SET o.payment = null WHERE o.branch.branchId = :branchId")
    void clearPaymentReferencesByBranchId(@Param("branchId") int branchId);

    @Transactional
    void deleteByUser_Id(int userId);

    @Transactional
    void deleteByBranch_BranchId(int branchId);
}