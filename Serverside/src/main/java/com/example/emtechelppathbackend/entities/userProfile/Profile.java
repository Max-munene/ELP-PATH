package com.example.emtechelppathbackend.entities.userProfile;

import com.example.emtechelppathbackend.security.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String title;
    private String website;
    private Long phoneNo;
// a user can have only one profile
    @OneToOne
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private Users user;


}
