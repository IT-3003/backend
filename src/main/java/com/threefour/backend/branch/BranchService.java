package com.threefour.backend.branch;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch getBranchById(int branchid) {
        // Fetches all columns for the specific primary key
        return branchRepository.findById(branchid)
                .orElseThrow(() -> new RuntimeException("Branch not found with id: " + branchid));
    }


public Branch savebranch(Branch branch){

    return branchRepository.save(branch);


            }
    public void deleteBranch(int branchid) {

        if (!branchRepository.existsById(branchid)) {
            throw new RuntimeException("Branch not found.");
        }

        branchRepository.deleteById(branchid);
    }
        }


