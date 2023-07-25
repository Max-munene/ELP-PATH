package com.example.emtechelppathbackend.controllers;

import com.example.emtechelppathbackend.Utils.Base64Utils;
import com.example.emtechelppathbackend.dtos.FeedsDto;
import com.example.emtechelppathbackend.entities.Feeds;
import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;
import com.example.emtechelppathbackend.repositories.FeedsRepository;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.FeedsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feeds")
public class FeedsController {

    private final FeedsRepository feedsRepository;
    private final UserRepository userRepository;
    private final FeedsService feedsService;

    public FeedsController(FeedsRepository feedsRepository, UserRepository userRepository, FeedsService feedsService) {
        this.feedsRepository = feedsRepository;
        this.userRepository = userRepository;
        this.feedsService = feedsService;
    }


    @PostMapping("/{userId}/add")
    public ResponseEntity<?> uploadFeedImages(@RequestParam("file") MultipartFile file,@PathVariable(value = "userId") Long userId,@RequestParam("description") String description) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new UserDetailsNotFoundException("user with that id not found"));
        try {
            String encodedData = Base64Utils.encode(file.getBytes());
            Feeds feeds = new Feeds();
            feeds.setUser(users);
            feeds.setDescription(description);
            feeds.setName(file.getOriginalFilename());
            feeds.setData(encodedData);
            feeds.setType(file.getContentType());

            Feeds savedFeeds = feedsRepository.save(feeds);

            return new ResponseEntity<>(savedFeeds, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/{userId}/add/multiple")
    public ResponseEntity<?> uploadMultipleImages(@RequestParam("file") List<MultipartFile> files,@PathVariable(value = "userId") Long userId,@RequestParam("description") String description) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new UserDetailsNotFoundException("user with that id not found"));
        List<String> data = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                String encodedData = Base64Utils.encode(file.getBytes());
                Feeds feeds = new Feeds();
                feeds.setUser(users);
                feeds.setDescription(description);
                feeds.setName(file.getOriginalFilename());
                data.add(encodedData);
                feeds.setType(file.getContentType());
                Feeds savedFeeds = feedsRepository.save(feeds);
                return new ResponseEntity<>(savedFeeds, HttpStatus.CREATED);
            } catch (IOException e) {
                return new ResponseEntity<>("Failed to upload image.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return (ResponseEntity<?>) data;
    }


    @PostMapping("/add/{user_id}")
    public ResponseEntity<FeedsDto> addFeeds(@PathVariable(value = "user_id")Long user_id, @RequestBody FeedsDto feedsDto){
        return new ResponseEntity<>(feedsService.addFeeds(user_id, feedsDto), HttpStatus.CREATED);
        }
        @GetMapping("/{id}")
    public List<FeedsDto> getFeed(@PathVariable Long id) {
        return feedsService.getFeedById(id);
    }

    @GetMapping("/user/{id}")
    public List<FeedsDto> getUserFeeds(@PathVariable Long id) {
        return feedsService.getFeedByUserId(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<FeedsDto> updateFeedsById(@PathVariable Long id, @RequestBody FeedsDto feedsDto) {
        FeedsDto updateFeed = (FeedsDto) feedsService.updateFeedsById(id, feedsDto);
        return new ResponseEntity<>(updateFeed, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFeed(@PathVariable Long id) {
        Feeds feed = feedsRepository.findById(id).orElseThrow();
        feedsRepository.delete(feed);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public List<FeedsDto> getAllUsersFeeds() {
        return feedsService.getAllUsersFeeds();
    }
}
