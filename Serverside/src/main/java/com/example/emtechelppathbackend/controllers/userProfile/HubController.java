package com.example.emtechelppathbackend.controllers.userProfile;

import com.example.emtechelppathbackend.dtos.userProfile.HubDto;
import com.example.emtechelppathbackend.entities.userProfile.Hub;
import com.example.emtechelppathbackend.services.HubService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hubs")
public class HubController {
    private final HubService hubService;
    private final ModelMapper modelMapper;

    public HubController(HubService hubService, ModelMapper modelMapper) {
        this.hubService = hubService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/create")
    public ResponseEntity<HubDto> createHub(@RequestBody HubDto hubDto){
        return new ResponseEntity<>(hubService.createHub(hubDto), HttpStatus.CREATED);
    }
    @GetMapping("/{hubId}/view")
    public ResponseEntity<HubDto> getHubsByUserId(@PathVariable(value = "hubId")Long hubId){
        return ResponseEntity.ok(hubService.getHubsById(hubId));
    }
    @GetMapping("/all")
    ResponseEntity<List<HubDto>>getAllHubs(){
        return ResponseEntity.ok(hubService.getAllHubs().stream()
                .map(hub -> modelMapper.map(hub, HubDto.class)).toList());
    }

    @PostMapping("/{userId}/{hubId}/join")
    public ResponseEntity<Hub>joinHub(@PathVariable(value = "userId")Long userId,@PathVariable(value = "hubId")Long hubId){
        Hub joinedHub = hubService.joinHub(userId, hubId);
        return new ResponseEntity<>(joinedHub,HttpStatus.CREATED);
    }
    @PostMapping("/{userId}/{hubId}/leave")
    public ResponseEntity<Hub>leaveHub(@PathVariable(value = "userId")Long userId,@PathVariable(value = "hubId")Long hubId){
        Hub leftHub = hubService.leaveThehub(userId, hubId);
        return new ResponseEntity<>(leftHub,HttpStatus.OK);
    }
}
