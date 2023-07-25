package com.example.emtechelppathbackend.controllers.userProfile;

import com.example.emtechelppathbackend.dtos.userProfile.BioDto;
import com.example.emtechelppathbackend.services.BioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bio")
public class BioController {
    private final BioService bioService;

    public BioController(BioService bioService) {
        this.bioService = bioService;
    }
    @GetMapping("/{userId}/view")
    public List<BioDto> viewBio(@PathVariable(value = "userId")Long userId){
        return bioService.viewBioByUserId(userId);
    }
    @PostMapping("/{userId}/add")
    public ResponseEntity<BioDto> createBio(@PathVariable(value = "userId")Long userId,@RequestBody BioDto bioDto){
        return new ResponseEntity<>(bioService.addBio(userId,bioDto), HttpStatus.CREATED);
    }
    @PutMapping("/{userId}/{bioId}/update")
    public ResponseEntity<BioDto> updateBio(@PathVariable(value = "userId")Long userId,@PathVariable(value = "bioId")Long bioId,@RequestBody BioDto bioDto){
        BioDto newBio  = bioService.updateBioByUserId(userId, bioId, bioDto);
        return new ResponseEntity<>(newBio,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}/{bioId}/delete")
    public ResponseEntity<?>deleteBio(@PathVariable (value = "userId")Long userId,@PathVariable(value = "bioId")Long bioId){
        bioService.deleteBio(userId, bioId);
        return new ResponseEntity<>("bio deleted",HttpStatus.OK);
    }
}
