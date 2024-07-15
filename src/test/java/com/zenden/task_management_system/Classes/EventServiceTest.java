package com.zenden.task_management_system.Classes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateEventDTO;
import com.zenden.task_management_system.Classes.DTO.ReadEventDTO;
import com.zenden.task_management_system.Classes.Filters.Event.EventFilter;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.CategoryRepository;
import com.zenden.task_management_system.Repositories.EventRepository;
import com.zenden.task_management_system.Repositories.LocationRepository;
import com.zenden.task_management_system.Services.EventService;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEventById() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Test Event");

        ReadEventDTO eventDTO = new ReadEventDTO();
        eventDTO.setId(1L);
        eventDTO.setName("Test Event");

        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));
        when(mapper.map(any(Event.class))).thenReturn(eventDTO);

        ReadEventDTO result = eventService.getEventById(1L);
        assertNotNull(result);
        assertEquals("Test Event", result.getName());
    }

    @Test
    void testGetAllEvents() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Test Event");

        ReadEventDTO eventDTO = new ReadEventDTO();
        eventDTO.setId(1L);
        eventDTO.setName("Test Event");

        List<Event> events = Arrays.asList(event);
        Page<Event> page = new PageImpl<>(events);
        when(eventRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(mapper.map(any(Event.class))).thenReturn(eventDTO);

        Page<ReadEventDTO> result = eventService.getAllEvents(0, 1, "name");
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Event", result.getContent().get(0).getName());
    }

    @Test
    void testGetAllEventsByFilter() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Test Event");

        ReadEventDTO eventDTO = new ReadEventDTO();
        eventDTO.setId(1L);
        eventDTO.setName("Test Event");

        List<Event> events = Arrays.asList(event);
        Page<Event> page = new PageImpl<>(events);
        when(eventRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);
        when(mapper.map(any(Event.class))).thenReturn(eventDTO);

        EventFilter eventFilter = new EventFilter();
        eventFilter.setName("Test Event");

        Page<ReadEventDTO> result = eventService.getAllEventsByFilter(0, 1, "name", eventFilter);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Event", result.getContent().get(0).getName());
    }

    @Test
    void testCreateEvent() {
        Event event = new Event();
        event.setName("Test Event");

        CreateEditUpdateEventDTO eventDTO = new CreateEditUpdateEventDTO();
        eventDTO.setName("Test Event");

        when(mapper.map(any(CreateEditUpdateEventDTO.class))).thenReturn(event);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event result = eventService.createEvent(eventDTO);
        assertNotNull(result);
        assertEquals("Test Event", result.getName());
    }

    @Test
    void testUpdateEvent() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Test Event");
    
        CreateEditUpdateEventDTO eventDTO = new CreateEditUpdateEventDTO();
        eventDTO.setName("Updated Event");
        eventDTO.setLocationId(1L);
        eventDTO.setCategoryId(1L);
    
        Location location = new Location();
        location.setId(1L);
    
        Category category = new Category();
        category.setId(1L);
    
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(location));
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(eventRepository.save(any(Event.class))).thenReturn(event);
    
        Event result = eventService.updateEvent(1L, eventDTO);
        assertNotNull(result);
        assertEquals("Updated Event", result.getName());
    }
    

    @Test
    void testDeleteEvent() {
        doNothing().when(eventRepository).deleteById(anyLong());
        eventService.deleteEvent(1L);
        verify(eventRepository, times(1)).deleteById(1L);
    }
}
