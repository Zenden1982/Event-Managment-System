package com.zenden.task_management_system.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zenden.task_management_system.Classes.Category;
import com.zenden.task_management_system.Classes.Event;
import com.zenden.task_management_system.Classes.Location;
import com.zenden.task_management_system.Classes.Participant;
import com.zenden.task_management_system.Classes.Ticket;
import com.zenden.task_management_system.Classes.DTO.CategoryDTO;
import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateEventDTO;
import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateTicketDTO;
import com.zenden.task_management_system.Classes.DTO.LocationDTO;
import com.zenden.task_management_system.Classes.DTO.ParticipantDTO;
import com.zenden.task_management_system.Classes.DTO.ReadEventDTO;
import com.zenden.task_management_system.Classes.DTO.ReadTicketDTO;
import com.zenden.task_management_system.Repositories.CategoryRepository;
import com.zenden.task_management_system.Repositories.EventRepository;
import com.zenden.task_management_system.Repositories.LocationRepository;
import com.zenden.task_management_system.Repositories.ParticipantRepository;
import com.zenden.task_management_system.Repositories.TicketRepository;

@Component
public class Mapper {
    
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantRepository participantRepository;
    
    public CategoryDTO map(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public Category map(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public ReadEventDTO map(Event event) {
        ReadEventDTO eventDTO = new ReadEventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setCategory(map(event.getCategory()));
        eventDTO.setLocation(map(event.getLocation()));
        eventDTO.setCreatedAt(event.getCreatedAt());
        eventDTO.setUpdatedAt(event.getUpdatedAt());
        eventDTO.setDate(event.getDate());
        return eventDTO;
    }

    public Event map(CreateEditUpdateEventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(locationRepository.findById(eventDTO.getLocationId()).orElseGet(() -> new Location()));
        event.setCategory(categoryRepository.findById(eventDTO.getCategoryId()).orElseGet(() -> new Category()));
        event.setDate(eventDTO.getDate());
        return event;
    }

    public Location map(LocationDTO locationDTO) {
        Location location = new Location();
        location.setId(locationDTO.getId());
        location.setName(locationDTO.getName());
        location.setAddress(locationDTO.getAddress());
        location.setCapacity(locationDTO.getCapacity());
        return location;
    }

    public LocationDTO map(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setAddress(location.getAddress());
        locationDTO.setCapacity(location.getCapacity());
        return locationDTO;
    }

    public Participant map(ParticipantDTO participant) {
        Participant participant1 = new Participant();
        participant1.setId(participant.getId());
        participant1.setName(participant.getName());
        participant1.setEmail(participant.getEmail());
        return participant1;
    }

    public ParticipantDTO map(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participant.getId());
        participantDTO.setName(participant.getName());
        participantDTO.setEmail(participant.getEmail());
        return participantDTO;
    }
    
    public Ticket map(CreateEditUpdateTicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setParticipant(participantRepository.findById(ticketDTO.getParticipantId()).orElseGet(() -> new Participant()));
        ticket.setEvent(eventRepository.findById(ticketDTO.getEventId()).orElseGet(() -> new Event()));
        return ticket;
    }

    public ReadTicketDTO map(Ticket ticket) {
        ReadTicketDTO readTicketDTO = new ReadTicketDTO();
        readTicketDTO.setId(ticket.getId());
        readTicketDTO.setEvent(map(ticket.getEvent()));
        readTicketDTO.setParticipant(map(ticket.getParticipant()));
        return readTicketDTO;
    }
}
