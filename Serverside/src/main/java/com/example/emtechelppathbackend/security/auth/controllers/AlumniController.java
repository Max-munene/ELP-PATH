package com.example.emtechelppathbackend.security.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@PreAuthorize("hasRole('ALUMNI')" + "|| hasRole('ADMIN')")
@RequestMapping("/alumni")

public class AlumniController {
    @GetMapping("/read")
//    @PreAuthorize("hasAuthority('admin:read')" + "|| hasAuthority('alumni:read')")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello, no big deal...just a regular page for admins and alumni");
    }

    @PreAuthorize("hasAuthority('alumni:create')")
    @GetMapping("/create")
    public ResponseEntity<String> crate() {
        return ResponseEntity.ok("Hello from here, no big deal...just a regular page for alumni only");
    }

}
