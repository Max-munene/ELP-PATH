package com.example.emtechelppathbackend.services;

import com.example.emtechelppathbackend.dtos.EventsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventsService {
    List<EventsDto> getAllEvents();
    EventsDto addEvent(Long user_id, EventsDto eventsDto);
    List<EventsDto> getEventById(Long Id);
    EventsDto updateEventById(Long id, EventsDto eventsDto);
    List<EventsDto> getEventByUserId(Long user_id);
}
