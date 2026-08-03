package com.threefour.backend.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @org.springframework.transaction.annotation.Transactional
    void deleteByProduct_ItemId(Long itemId);

    @org.springframework.transaction.annotation.Transactional
    void deleteByOrder_OrderId(int orderId);

    @org.springframework.transaction.annotation.Transactional
    void deleteByOrder_Branch_BranchId(int branchId);

    @org.springframework.transaction.annotation.Transactional
    void deleteByOrder_User_Id(int userId);
}