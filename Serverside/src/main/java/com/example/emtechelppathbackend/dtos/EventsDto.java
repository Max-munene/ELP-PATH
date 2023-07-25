package com.example.emtechelppathbackend.dtos;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventsDto {
    private Long id;
    private String description;
    private String eventDate;
    private String organizer;
    private String eventLink;
    private String location;
    private Long user;
}
