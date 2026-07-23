package com.threefour.backend.branch;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branch")
public class BranchController {
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    private final BranchService branchService;


    @GetMapping("/hasi")
    public String hello() {
        return "Hello Himandiiiiiii";
    }
    @PostMapping("/create")
    public ResponseEntity<Branch> create(@RequestBody Branch branch) {
        Branch savedBranch = branchService.savebranch(branch);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }
    @GetMapping("/{branchid}")
    public Branch getBranchById(@PathVariable int branchid) {
        return branchService.getBranchById(branchid);
    }



        @DeleteMapping("/{branchid}")
        public String deleteBranch(@PathVariable int branchid) {
            branchService.deleteBranch(branchid);
            return "Branch deleted successfully.";
        }
    }
