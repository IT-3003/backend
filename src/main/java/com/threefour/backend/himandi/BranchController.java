package com.threefour.backend.himandi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controller")
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
        Branch savedBranch = branchService.saveHimandi(branch);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }


}