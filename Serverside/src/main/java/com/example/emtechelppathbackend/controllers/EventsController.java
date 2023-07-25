package com.example.emtechelppathbackend.controllers;

import com.example.emtechelppathbackend.Utils.Base64Utils;
import com.example.emtechelppathbackend.dtos.EventsDto;
import com.example.emtechelppathbackend.entities.Events;
import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;
import com.example.emtechelppathbackend.repositories.EventsRepo;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.EventsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventsRepo eventsRepository;
    private final UserRepository userRepository;
    private final EventsService eventsService;

    public EventsController(EventsRepo eventsRepository, UserRepository userRepository, EventsService eventsService) {
        this.eventsRepository = eventsRepository;
        this.userRepository = userRepository;
        this.eventsService = eventsService;
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> uploadEventImages(@RequestParam("file") MultipartFile file, @PathVariable(value = "userId") Long userId, @RequestParam("description") String description, @RequestParam("eventDate") String eventsDate, @RequestParam("location") String location) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new UserDetailsNotFoundException("user with that id not found"));
        try {
            String encodedData = Base64Utils.encode(file.getBytes());
            Events events = new Events();
            events.setUser(users);
            events.setDescription(description);
            events.setEventDate(eventsDate);
            events.setLocation(location);
            events.setName(file.getOriginalFilename());
            events.setData(encodedData);
            events.setType(file.getContentType());

            Events savedEvents = eventsRepository.save(events);

            return new ResponseEntity<>(savedEvents, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/{userId}/add/multiple")
    public ResponseEntity<?> uploadMultipleImages(@RequestParam("file") List<MultipartFile> files, @PathVariable(value = "userId") Long userId, @RequestParam("description") String description) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new UserDetailsNotFoundException("user with that id not found"));
        List<String> data = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                String encodedData = Base64Utils.encode(file.getBytes());
                Events events = new Events();
                events.setUser(users);
                events.setDescription(description);
                events.setName(file.getOriginalFilename());
                data.add(encodedData);
                events.setType(file.getContentType());
                Events savedEvents = eventsRepository.save(events);
                return new ResponseEntity<>(savedEvents, HttpStatus.CREATED);
            } catch (IOException e) {
                return new ResponseEntity<>("Failed to upload image.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return (ResponseEntity<?>) data;
    }


    @PostMapping("/add/{user_id}")
    public ResponseEntity<EventsDto> addEvents(@PathVariable(value = "user_id")Long user_id, @RequestBody EventsDto eventsDto){
        return new ResponseEntity<>(eventsService.addEvent(user_id, eventsDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public List<EventsDto> getFeed(@PathVariable Long id) {
        return eventsService.getEventById(id);
    }

    @GetMapping("/user/{id}")
    public List<EventsDto> getUserEvents(@PathVariable Long id) {
        return eventsService.getEventByUserId(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EventsDto> updateEventsById(@PathVariable Long id, @RequestBody EventsDto eventsDto) {
        EventsDto updateFeed = (EventsDto) eventsService.updateEventById(id, eventsDto);
        return new ResponseEntity<>(updateFeed, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFeed(@PathVariable Long id) {
        Events feed = eventsRepository.findById(id).orElseThrow();
        eventsRepository.delete(feed);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public List<EventsDto> getAllUsersEvents() {
        return eventsService.getAllEvents();
    }


}
