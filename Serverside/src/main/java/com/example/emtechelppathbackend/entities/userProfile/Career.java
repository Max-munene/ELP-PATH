package com.example.emtechelppathbackend.entities.userProfile;

import com.example.emtechelppathbackend.security.entities.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String title;
    @Length(max = 2000)
    private String description;

    @NotNull(message = "start Date should not be null")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate start_date;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = true)
    private LocalDate  end_date;

    //One User can have many careers
    @JoinColumn(name = "users_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;
}
