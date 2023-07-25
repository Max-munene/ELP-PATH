package com.example.emtechelppathbackend.services.ServiceImpl;

import com.example.emtechelppathbackend.dtos.userProfile.HubDto;
import com.example.emtechelppathbackend.entities.userProfile.Hub;
import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;

import com.example.emtechelppathbackend.repositories.HubRepo;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.HubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HubServiceImpl implements HubService {
    private final UserRepository userRepository;
    private final HubRepo hubRepo;

    public HubServiceImpl(UserRepository userRepository, HubRepo hubRepo) {
        this.userRepository = userRepository;
        this.hubRepo = hubRepo;
    }

    @Override


    public HubDto createHub(HubDto hubDto) {
        Hub hub  = matToEntity(hubDto);
        hub.setId(hubDto.getId());
        hub.setName(hubDto.getName());
        hub.setDescription(hubDto.getDescription());
        Hub newHub = hubRepo.save(hub);
        return mapToDto(newHub);
    }

    @Override
    public HubDto getHubsById(Long hubId) {
        Hub hub = hubRepo.findById(hubId).orElseThrow(()->new UserDetailsNotFoundException("hubs not found"));
        return mapToDto(hub);
    }

    @Override
    public List<Hub> getAllHubs() {
        List<Hub> hubs = hubRepo.findAll();
        return hubs;
    }

    @Override
    public Hub joinHub(Long userId, Long hubId) {
        Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Hub hubs =  hubRepo.findById(hubId).orElseThrow(()->new RuntimeException("Hubs not found"));
        List<Users> members = hubs.getUser();
        members.add(users);
        hubs.setUser(members);
        return hubRepo.save(hubs);
    }

    @Override
    public Hub leaveThehub(Long userId, Long hubId) {
        Users users = userRepository.findById(userId).orElseThrow(()->new UserDetailsNotFoundException("user not found"));
        Hub hubs =  hubRepo.findById(hubId).orElseThrow(()->new UserDetailsNotFoundException("Hubs not found"));
        List<Users> members = hubs.getUser();
        members.remove(users);
        hubs.setUser(members);
        return hubRepo.save(hubs);
    }

    private HubDto mapToDto(Hub hub){
        HubDto hubDto = new HubDto();
        hubDto.setId(hub.getId());
        hubDto.setName(hub.getName());

        hubDto.setDescription(hub.getDescription());
        return hubDto;
    }
    private Hub matToEntity(HubDto hubDto){
        Hub hub = new Hub();
        hub.setId(hubDto.getId());
        hub.setName(hubDto.getName());
        hub.setDescription(hubDto.getDescription());

        return hub;
    }
}
