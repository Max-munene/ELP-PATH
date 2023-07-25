package com.example.emtechelppathbackend.branchchampion.services.scholarService;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.Branch;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BranchService {

    List<Branch> displayAllBranches();

    Optional<Branch> displayBranchById(Long id);

    Branch addNewBranch(Branch branch);

    Branch updateBranchById(Long id, Branch updatedBranch);

    boolean deleteBranchById(Long id);
}
