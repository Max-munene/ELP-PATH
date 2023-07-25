package com.example.emtechelppathbackend.services;

import com.example.emtechelppathbackend.dtos.userProfile.HubDto;

import com.example.emtechelppathbackend.entities.userProfile.Hub;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HubService {

    HubDto createHub(HubDto hubDto);
    HubDto getHubsById(Long hubId);
    List<Hub>getAllHubs();
    Hub joinHub(Long userId, Long hubId);
    Hub leaveThehub(Long userId,Long hubId);

}
