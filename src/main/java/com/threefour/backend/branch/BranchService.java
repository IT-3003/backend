package com.threefour.backend.branch;

import org.springframework.stereotype.Service;
import java.util.List;

import com.threefour.backend.order.OrderRepository;
import com.threefour.backend.order.OrderItemRepository;
import com.threefour.backend.payment.PaymentRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;

    public BranchService(BranchRepository branchRepository, OrderRepository orderRepository,
                         PaymentRepository paymentRepository, OrderItemRepository orderItemRepository) {
        this.branchRepository = branchRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Branch getBranchById(int branchid) {
        return branchRepository.findById(branchid)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchid));
    }


    public Branch savebranch(Branch branch) {

        return branchRepository.save(branch);
    }

    @Transactional
    public void deleteBranch(int branchid) {
        if (!branchRepository.existsById(branchid)) {
            throw new RuntimeException("Branch not found.");
        }
        orderRepository.clearPaymentReferencesByBranchId(branchid);
        paymentRepository.deleteByOrder_Branch_BranchId(branchid);
        orderItemRepository.deleteByOrder_Branch_BranchId(branchid);
        orderRepository.deleteByBranch_BranchId(branchid);
        branchRepository.deleteById(branchid);
    }

    public Branch updateBranch(int branchid, Branch updatedBranch) {

        Branch existingBranch = branchRepository.findById(branchid)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        existingBranch.setBranchName(updatedBranch.getBranchName());
        existingBranch.setAddress(updatedBranch.getAddress());
        existingBranch.setPhoneNumber(updatedBranch.getPhoneNumber());
        existingBranch.setManagerId(updatedBranch.getManagerId());
        existingBranch.setOpeningHours(updatedBranch.getOpeningHours());
        existingBranch.setUpdatedDate(updatedBranch.getUpdatedDate());

        return branchRepository.save(existingBranch);
    }

    public List<Branch> getAllBranches(boolean includeInactive) {
        if (includeInactive) {
            return branchRepository.findAll();
        } else {
            return branchRepository.findByIsActiveTrue();
        }
    }
}