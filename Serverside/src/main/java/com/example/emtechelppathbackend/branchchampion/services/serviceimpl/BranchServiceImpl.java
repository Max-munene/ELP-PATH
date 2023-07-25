package com.example.emtechelppathbackend.branchchampion.services.serviceimpl;

import com.example.emtechelppathbackend.branchchampion.entities.scholars.Branch;
import com.example.emtechelppathbackend.branchchampion.repositories.BranchRepository;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public List<Branch> displayAllBranches() {
        return branchRepository.findAll();
    }

    @Override
    public Optional<Branch> displayBranchById(Long id) {
        return branchRepository.findById(id);
    }

    @Override
    public Branch addNewBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Branch updateBranchById(Long id, Branch updatedBranch) {
        Optional<Branch> existingBranch = branchRepository.findById(id);
        if (existingBranch.isPresent()){
            Branch branch = existingBranch.get();
            branch.setBranchName(updatedBranch.getBranchName());
            branch.setAdditionalInformation(updatedBranch.getAdditionalInformation());
            return branchRepository.save(branch);
        }
        return null; // todo : exception
    }

    @Override
    public boolean deleteBranchById(Long id) {
        if (branchRepository.existsById(id)){
            branchRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
