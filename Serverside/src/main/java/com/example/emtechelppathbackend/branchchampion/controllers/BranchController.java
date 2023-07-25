package com.example.emtechelppathbackend.branchchampion.controllers;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.Branch;
import com.example.emtechelppathbackend.branchchampion.repositories.BranchRepository;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/all")
    public ResponseEntity<List<Branch>> displayAllBranches() {
        List<Branch> branches = branchService.displayAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<Branch> displayBranchById(@PathVariable Long id) {
        Optional<Branch> branch = branchService.displayBranchById(id);
        if (branch.isPresent()) {
            return new ResponseEntity<>(branch.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-new-branch")
    public ResponseEntity<Branch> addNewBranch(@RequestBody Branch branch) {
        Branch addedBranch = branchService.addNewBranch(branch);
        return new ResponseEntity<>(addedBranch, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Branch> updateBranchById(@PathVariable Long id, @RequestBody Branch updatedBranch) {
        Branch updated = branchService.updateBranchById(id, updatedBranch);
        if (updated != null){
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBranchById(@PathVariable Long id){
        boolean deleted = branchService.deleteBranchById(id);
        if (deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
