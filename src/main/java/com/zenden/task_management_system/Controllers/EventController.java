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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/events")
@Slf4j
@Tag(name = "EventController", description = "Контроллер для управления событиями")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private Mapper mapper;

    @Operation(summary = "Получить все события с пагинацией и сортировкой")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение списка событий", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @Transactional
    @GetMapping
    public ResponseEntity<Page<ReadEventDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "id") String sortBy) {
        log.info("Получение всех событий, страница: {}, размер: {}, сортировка по: {}", page, size, sortBy);
        return ResponseEntity.ok(eventService.getAllEvents(page, size, sortBy));
    }

    @Operation(summary = "Получить все события по фильтру с пагинацией и сортировкой")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение отфильтрованного списка событий", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @Transactional
    @PostMapping("/filter")
    public ResponseEntity<Page<ReadEventDTO>> findAllByFilter(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size,
                                                              @RequestParam(defaultValue = "id") String sortBy,
                                                              @RequestBody EventFilter filter) {
        log.info("Получение всех событий по фильтру, страница: {}, размер: {}, сортировка по: {}, фильтр: {}", page, size, sortBy, filter);
        return ResponseEntity.ok(eventService.getAllEventsByFilter(page, size, sortBy, filter));
    }

    @Operation(summary = "Получить событие по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение события", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReadEventDTO.class))),
        @ApiResponse(responseCode = "404", description = "Событие не найдено", 
                     content = @Content(mediaType = "application/json"))
    })
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ReadEventDTO> findById(@PathVariable long id) {
        log.info("Получение события по ID: {}", id);
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @Operation(summary = "Добавить новое событие")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Событие успешно добавлено", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReadEventDTO.class)))
    })
    @Transactional
    @PostMapping
    public ResponseEntity<ReadEventDTO> addEvent(@RequestBody CreateEditUpdateEventDTO event) {
        log.info("Добавление нового события: {}", event);
        ReadEventDTO entity = mapper.map(eventService.createEvent(event));
        return ResponseEntity.ok(entity);
    }

    @Operation(summary = "Обновить событие по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Событие успешно обновлено", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReadEventDTO.class))),
        @ApiResponse(responseCode = "404", description = "Событие не найдено", 
                     content = @Content(mediaType = "application/json"))
    })
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ReadEventDTO> putMethodName(@PathVariable long id, @RequestBody CreateEditUpdateEventDTO event) {
        log.info("Обновление события по ID: {}, данные: {}", id, event);
        ReadEventDTO entity = mapper.map(eventService.updateEvent(id, event));
        return ResponseEntity.ok(entity);
    }

    @Operation(summary = "Удалить событие по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Событие успешно удалено", 
                     content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Событие не найдено", 
                     content = @Content(mediaType = "application/json"))
    })
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMethodName(@PathVariable long id) {
        log.info("Удаление события по ID: {}", id);
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Deleted");
    }
}

