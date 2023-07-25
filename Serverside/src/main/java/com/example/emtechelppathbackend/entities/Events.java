package com.example.emtechelppathbackend.entities;

import com.example.emtechelppathbackend.security.entities.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Lob
    @Column(length = 10485760)
    private String data;

    private String type;

    @Column(name = "description",length = 2000)
    private String description;

    @Column(name = "event_date")
    private String eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;

    @Column(name = "link")
    private String eventLink;
    @Column(name = "location")
    private String location;

    @Column(name = "organizer")
    private String organizer;


}
