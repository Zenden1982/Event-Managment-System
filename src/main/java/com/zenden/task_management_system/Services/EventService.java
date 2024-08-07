package com.zenden.task_management_system.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.Event;
import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateEventDTO;
import com.zenden.task_management_system.Classes.DTO.ReadEventDTO;
import com.zenden.task_management_system.Classes.Filters.Event.EventFilter;
import com.zenden.task_management_system.Classes.Filters.Event.EventSpecifications;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.CategoryRepository;
import com.zenden.task_management_system.Repositories.EventRepository;
import com.zenden.task_management_system.Repositories.LocationRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private Mapper mapper;

    public ReadEventDTO getEventById(long id) {
        return mapper.map(eventRepository.findById(id).get());
    }

    public Page<ReadEventDTO> getAllEvents(int page, int size, String sortBy) {
        return eventRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy))).map(mapper::map);
    }

    public Page<ReadEventDTO> getAllEventsByFilter(int page, int size, String sortBy, EventFilter eventFilter) {
        Specification<Event> spec = Specification.where(null);
        if (eventFilter.getName() != null) {
            spec = spec.and(EventSpecifications.nameLike(eventFilter.getName()));
        }
        if (eventFilter.getDescription() != null) {
            spec = spec.and(EventSpecifications.descriptionLike(eventFilter.getDescription()));
        }
        if (eventFilter.getDate() != null) {
            spec = spec.and(EventSpecifications.dateAfter(eventFilter.getDate()));
        }
        if (eventFilter.getLocationId() != null) {
            spec = spec.and(EventSpecifications.locationIdEquals(eventFilter.getLocationId()));
        }
        if (eventFilter.getCategoryId() != null) {
            spec = spec.and(EventSpecifications.categoryIdEquals(eventFilter.getCategoryId()));
        }
        return eventRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortBy))).map(mapper::map);
    }
       

    public Event createEvent(CreateEditUpdateEventDTO eventDTO) {
        return eventRepository.save(mapper.map(eventDTO));
    }

    public Event updateEvent(Long id, CreateEditUpdateEventDTO eventDTO) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            event.setName(eventDTO.getName());
            event.setDescription(eventDTO.getDescription());
            event.setDate(eventDTO.getDate());
            event.setLocation(locationRepository.findById(eventDTO.getLocationId()).get());
            event.setCategory(categoryRepository.findById(eventDTO.getCategoryId()).get());
            return eventRepository.save(event);
        } else {
            throw new RuntimeException("Event not found");
        }
    }

    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }
}
