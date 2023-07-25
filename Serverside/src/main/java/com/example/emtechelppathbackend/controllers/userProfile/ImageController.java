package com.example.emtechelppathbackend.controllers.userProfile;

import com.example.emtechelppathbackend.Utils.Base64Utils;
import com.example.emtechelppathbackend.entities.Feeds;
import com.example.emtechelppathbackend.entities.userProfile.Image;
import com.example.emtechelppathbackend.exceptions.UserDetailsNotFoundException;
import com.example.emtechelppathbackend.repositories.FeedsRepository;
import com.example.emtechelppathbackend.repositories.ImageRepository;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final FeedsRepository feedsRepository;


    public ImageController(ImageRepository imageRepository, UserRepository userRepository,FeedsRepository feedsRepository ) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.feedsRepository = feedsRepository;

    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,@PathVariable(value = "userId") Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserDetailsNotFoundException("user with that id not found"));
        if (user.getImage() != null) {
            throw new UserDetailsNotFoundException("userProfile already exists You can edit it");
        }
            try {
                String encodedData = Base64Utils.encode(file.getBytes());
                Image image = new Image();
                image.setUser(user);
                image.setName(file.getOriginalFilename());
                image.setData(encodedData);
                image.setType(file.getContentType());


                Image savedImage = imageRepository.save(image);

                return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
            } catch (IOException e) {
                return new ResponseEntity<>("Failed to upload image.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }
    @GetMapping("/{userId}/view")
    public ResponseEntity<?> getImage(@PathVariable("userId") Long userId) {
        Optional<Image> optionalImage = imageRepository.findByUserId(userId);
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            image.setData(image.getData());
            image.setType(image.getType());
            //byte[] decodedData = Base64Utils.decode(image.getData());
            //return ResponseEntity.ok(decodedData);
            return ResponseEntity.ok(image);
        } else {
            return new ResponseEntity<>("Image not found.", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{userId}/update")
    public ResponseEntity<?> updateImage(@RequestParam("file")MultipartFile file,@PathVariable(value = "userId")Long userId){
        Users user = userRepository.findById(userId).orElseThrow(() -> new UserDetailsNotFoundException("user with that id not found"));
        Image image = user.getImage();
        if (image == null){
            throw  new UserDetailsNotFoundException("image not found");
        }
        try {
            String encodedData = Base64Utils.encode(file.getBytes());
            image.setName(file.getOriginalFilename());
            image.setData(encodedData);
            image.setType(file.getContentType());
            Image newImage = imageRepository.save(image);
            return new ResponseEntity<>(newImage,HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
