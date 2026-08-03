package com.threefour.backend.branch;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch getBranchById(int branchid) {
        return branchRepository.findById(branchid)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchid));
    }


    public Branch savebranch(Branch branch) {

        return branchRepository.save(branch);
    }

    public void deleteBranch(int branchid) {

        if (!branchRepository.existsById(branchid)) {
            throw new RuntimeException("Branch not found.");
        }

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