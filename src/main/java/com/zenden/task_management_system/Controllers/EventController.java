package com.zenden.task_management_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateEventDTO;
import com.zenden.task_management_system.Classes.DTO.ReadEventDTO;
import com.zenden.task_management_system.Classes.Filters.Event.EventFilter;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Services.EventService;


@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private Mapper mapper;

    @Transactional
    @GetMapping
    public ResponseEntity<Page<ReadEventDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue="id") String sortBy) {
        return ResponseEntity.ok(eventService.getAllEvents(page, size, sortBy));
    }

    @Transactional
    @PostMapping("/filter")
    public ResponseEntity<Page<ReadEventDTO>> findAllByFilter(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy, @RequestBody EventFilter filter) {
                return ResponseEntity.ok(eventService.getAllEventsByFilter(page, size, sortBy, filter));
            }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ReadEventDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ReadEventDTO> addEvent(@RequestBody CreateEditUpdateEventDTO event) {

        ReadEventDTO entity = mapper.map(eventService.createEvent(event));
        
        return ResponseEntity.ok(entity);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ReadEventDTO> putMethodName(@PathVariable long id, @RequestBody CreateEditUpdateEventDTO event) {
        ReadEventDTO entity = mapper.map(eventService.updateEvent(id, event));
        return ResponseEntity.ok(entity);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMethodName(@PathVariable long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Deleted");
    }
    
}
