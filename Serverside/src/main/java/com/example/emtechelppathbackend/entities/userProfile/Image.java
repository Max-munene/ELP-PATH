package com.example.emtechelppathbackend.entities.userProfile;


import com.example.emtechelppathbackend.entities.Feeds;
import com.example.emtechelppathbackend.security.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Lob
    @Column(length = 10485760)
    private String data;
    private String type;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feed_id")
    @JsonIgnore
    private Feeds feed;
}
