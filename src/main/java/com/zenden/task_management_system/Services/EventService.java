package com.zenden.task_management_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.Event;
import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateEventDTO;
import com.zenden.task_management_system.Classes.DTO.ReadEventDTO;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.EventRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private Mapper mapper;

    public ReadEventDTO getEventById(long id) {
        return mapper.map(eventRepository.findById(id).get());
    }

    public Page<ReadEventDTO> getAllEvents(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return eventRepository.findAll(pageable).map(mapper::map);
    }

    public Event createEvent(CreateEditUpdateEventDTO eventDTO) {
        return eventRepository.save(mapper.map(eventDTO));
    }
}
