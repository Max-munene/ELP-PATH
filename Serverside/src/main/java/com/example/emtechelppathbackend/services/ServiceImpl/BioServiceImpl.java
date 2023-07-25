package com.example.emtechelppathbackend.services.ServiceImpl;

import com.example.emtechelppathbackend.dtos.userProfile.BioDto;
import com.example.emtechelppathbackend.entities.userProfile.Bio;
import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;
import com.example.emtechelppathbackend.repositories.BioRepository;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.BioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BioServiceImpl implements BioService {
    private final BioRepository bioRepository;
    private final UserRepository userRepository;

    public BioServiceImpl(BioRepository bioRepository, UserRepository userRepository) {
        this.bioRepository = bioRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BioDto addBio(Long userId, BioDto bioDto) {
        Bio bio = mapToEntity(bioDto);
        Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        if (users.getBio() !=null){
            throw new UserDetailsNotFoundException("User had bio already you can edit your bio");
        }
        bio.setUser(users);
        bio.setId(bioDto.getId());
        bio.setDescription(bio.getDescription());
        Bio newBio = bioRepository.save(bio);
        return mapToDto(newBio);
    }

    @Override
    public List<BioDto> viewBioByUserId(Long userId) {
        List<Bio> bios = bioRepository.findByUserId(userId);
        return bios.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public BioDto updateBioByUserId(Long userId, Long bioId, BioDto bioDto) {
        Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Bio bio = bioRepository.findById(bioId).orElseThrow(()->new RuntimeException("bio not found"));
        if (!Objects.equals(bio.getUser().getId(), users.getId())){
            throw new RuntimeException("this bio do not belong to this user");
        }
        bio.setDescription(bioDto.getDescription());
        return mapToDto(bio);
    }

    @Override
    public void deleteBio(Long userId, Long bioId) {
        Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Bio bio = bioRepository.findById(bioId).orElseThrow(()->new RuntimeException("bio not found"));
        if (!Objects.equals(bio.getUser().getId(), users.getId())){
            throw new RuntimeException("this bio do not belong to this user");
        }
        bioRepository.delete(bio);

    }
    private BioDto  mapToDto(Bio bio){
        BioDto bioDto = new BioDto();
        bioDto.setId(bio.getId());
        bioDto.setDescription(bio.getDescription());
        return bioDto;
    }
    private Bio mapToEntity(BioDto bioDto){
        Bio bio = new Bio();
        bio.setId(bioDto.getId());
        bio.setDescription(bioDto.getDescription());
        return bio;
    }
}
