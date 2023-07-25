package com.example.emtechelppathbackend.entities;


import com.example.emtechelppathbackend.security.entities.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feeds")
public class Feeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    @Column(length = 10485760)
    private String data;

    private String type;

    @Column(name = "description",length = 5000)
    private String description;


    @JsonFormat(pattern="yyyy-MM-dd:hh:mm:ss")
    @Column(name = "post_date")
    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;



@PrePersist
private void onCreate(){
    postDate = LocalDateTime.now();
}
}