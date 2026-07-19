package com.threefour.backend.branch;

import org.springframework.stereotype.Service;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public static String addnumbers(int a, int b){
        return "This sum is" + (a+b);}

public Branch saveHimandi(Branch branch){

    return branchRepository.save(branch);


            }
        }


