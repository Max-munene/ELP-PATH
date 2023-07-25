/*
package com.example.emtechelppathbackend.services.ServiceImpl;


import com.example.emtechelppathbackend.dtos.ScholarsDTO;
import com.example.emtechelppathbackend.entities.Scholar;
import com.example.emtechelppathbackend.repositories.ScholarsRepository;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.branchchampion.services.scholarService.ScholarService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScholarsServiceImpl implements ScholarService {
    private final ScholarsRepository scholarsRepo;
    private final UserRepository userRepository;

    public ScholarsServiceImpl(ScholarsRepository scholarsRepo, UserRepository userRepository) {
        this.scholarsRepo = scholarsRepo;
        this.userRepository = userRepository;
    }

    @Override
    public ScholarsDTO createScholar(ScholarsDTO scholarsDTO) {
        Scholar scholar = mapToEntity(scholarsDTO);
        scholar.setId(scholarsDTO.getId());
        scholar.setName(scholarsDTO.getName());
        scholar.setScholarNumber(scholarsDTO.getScholarNumber());
        scholar.setStatus(scholarsDTO.getStatus());
        scholar.setBranch(scholarsDTO.getBranch());
        scholar.setYearOfApplication(scholarsDTO.getYearOfApplication());

        Scholar scholar1 = scholarsRepo.save(scholar);
        return mapToDto(scholar1);
    }

    @Override
    public List<Scholar> viewAllScholars() {
        List <Scholar> scholars = scholarsRepo.findAll();
        return scholars;
    }

    @Override
    public List <ScholarsDTO> findScholarByName(String name) {
        List <Scholar> scholars = scholarsRepo.findScholarByName(name);
        return scholars.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List <ScholarsDTO> findScholarByYearOfApplication(String year) {
        List <Scholar> scholars = scholarsRepo.findScholarByYearOfApplication(year);
        return scholars.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List <ScholarsDTO> findScholarByBranch(String branch) {
        List <Scholar> scholars = scholarsRepo.findScholarByBranch(branch);
        return scholars.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List <ScholarsDTO> findScholarByStatus(String status) {
        List <Scholar> scholars = scholarsRepo.findScholarByStatus(status);
        return scholars.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ScholarsDTO updateScholar(Long scholarsId, ScholarsDTO scholarsDTO) {
       //Users users = userRepository.findById(scholarId).orElseThrow(()->new RuntimeException("user not found"));
        Scholar scholar = scholarsRepo.findById(scholarsId).orElseThrow(()->new RuntimeException("Scholar not found"));
       // if (!Objects.equals(profile.getUser().getId(), users.getId())){
         //   throw  new RuntimeException("this profile does not belong to this user");
       // }
        scholar.setId(scholarsDTO.getId());
        scholar.setName(scholarsDTO.getName());
        scholar.setStatus(scholarsDTO.getStatus());
        scholar.setScholarNumber(scholarsDTO.getScholarNumber());
        scholar.setBranch(scholarsDTO.getBranch());
        scholar.setYearOfApplication(scholarsDTO.getYearOfApplication());
        Scholar updatedScholar = scholarsRepo.save(scholar);
        return mapToDto(updatedScholar);
    }

    @Override
    public void deleteScholar(Long scholarsId) {
        //Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Scholar scholar = scholarsRepo.findById(scholarsId).orElseThrow(()->new RuntimeException("Scholar not found"));
        //if (!Objects.equals(profile.getUser().getId(),users.getId())){
        //    throw new RuntimeException("This profile is not yours");
        //}
        scholarsRepo.delete(scholar);
    }

    private ScholarsDTO mapToDto(Scholar scholar){
        ScholarsDTO scholarsDTO = new ScholarsDTO();
        scholarsDTO.setId(scholar.getId());
        scholarsDTO.setName(scholar.getName());
        scholarsDTO.setScholarNumber(scholar.getScholarNumber());
        scholarsDTO.setStatus(scholar.getStatus());
        scholarsDTO.setYearOfApplication(scholar.getYearOfApplication());
        scholarsDTO.setBranch(scholar.getBranch());
        return scholarsDTO;
    }
    private Scholar mapToEntity(ScholarsDTO scholarsDTO){
        Scholar scholar = new Scholar();
        scholar.setId(scholarsDTO.getId());
        scholar.setName(scholarsDTO.getName());
        scholar.setScholarNumber(scholarsDTO.getScholarNumber());
        scholar.setStatus(scholarsDTO.getStatus());
        scholar.setYearOfApplication(scholarsDTO.getYearOfApplication());
        scholar.setBranch(scholarsDTO.getBranch());
        return scholar;
    }
}*/
