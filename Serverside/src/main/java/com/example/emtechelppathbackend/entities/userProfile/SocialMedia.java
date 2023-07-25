package com.example.emtechelppathbackend.entities.userProfile;

import com.example.emtechelppathbackend.security.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "socials")
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String linkedIn;
    private String twitter;
    private String facebook;
    private String github;
    private String instagram;
    @JoinColumn(name = "users_id")
    @JsonIgnore
    @OneToOne
    private Users user;
}
