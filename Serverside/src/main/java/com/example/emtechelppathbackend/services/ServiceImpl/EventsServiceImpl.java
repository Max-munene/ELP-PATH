package com.example.emtechelppathbackend.services.ServiceImpl;

import com.example.emtechelppathbackend.dtos.EventsDto;

import com.example.emtechelppathbackend.entities.Events;
import com.example.emtechelppathbackend.entities.Feeds;
import com.example.emtechelppathbackend.repositories.EventsRepo;
import com.example.emtechelppathbackend.security.entities.Users;
import com.example.emtechelppathbackend.security.repositories.UserRepository;
import com.example.emtechelppathbackend.services.EventsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EventsServiceImpl implements EventsService {


    private final EventsRepo eventsRepository;
    private  final UserRepository usersRepository;


    public EventsServiceImpl(EventsRepo eventsRepository, UserRepository usersRepository) {
        this.eventsRepository = eventsRepository;
        this.usersRepository = usersRepository;

    }

    @Override
    public List<EventsDto> getAllEvents() {
        return eventsRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public EventsDto addEvent(Long user_id, EventsDto eventsDto) {
        Events events = mapToEntity(eventsDto);
        Users users = usersRepository.findById(user_id).orElseThrow(()->new RuntimeException("user not found"));
        events.setUser(users);
        events.setLocation(eventsDto.getLocation());
        events.setEventDate(eventsDto.getEventDate());
        events.setEventLink(eventsDto.getEventLink());
        events.setDescription(eventsDto.getDescription());
        events.setOrganizer(eventsDto.getOrganizer());
        Events newEvents = eventsRepository.save(events);
        return mapToDto(newEvents);
    }

    @Override
    public List<EventsDto> getEventById(Long Id) {
        return eventsRepository.findById(Id)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors
                        .toList());
    }

    @Override
    public EventsDto updateEventById(Long id, EventsDto eventsDto) {
        Events events = eventsRepository.findById(id).orElseThrow();
        eventsDto.setDescription(events.getDescription());
        eventsDto.setEventLink(events.getEventLink());
        eventsDto.setOrganizer(events.getOrganizer());
        eventsDto.setLocation(events.getLocation());
        eventsDto.setEventDate(events.getEventDate());
        return mapToDto(events);
    }

    @Override
    public List<EventsDto> getEventByUserId(Long user_id) {
        return eventsRepository.findById(user_id)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors
                        .toList());
    }

    private Events mapToEntity(EventsDto eventsDto) {
        Events events = new Events();
        events.setLocation(eventsDto.getLocation());
        events.setEventDate(eventsDto.getEventDate());
        events.setEventLink(eventsDto.getEventLink());
        events.setDescription(eventsDto.getDescription());
        events.setOrganizer(eventsDto.getOrganizer());
        return events;
    }
    private EventsDto mapToDto(Events events) {
        EventsDto eventsDto = new EventsDto();
        eventsDto.setId(events.getId());
        eventsDto.setDescription(events.getDescription());
        eventsDto.setEventLink(events.getEventLink());
        eventsDto.setOrganizer(events.getOrganizer());
        eventsDto.setLocation(events.getLocation());
        eventsDto.setEventDate(events.getEventDate());
        eventsDto.setUser(events.getUser().getId());
        return eventsDto;
    }
}
