package com.example.emtechelppathbackend.entities.userProfile;

import com.example.emtechelppathbackend.security.entities.Users;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bio")
public class Bio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 5000)
    private String description;
    @OneToOne
    @JoinColumn(name = "users_id")
    private Users user;
}
