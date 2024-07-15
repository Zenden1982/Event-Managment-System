package com.zenden.task_management_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateTicketDTO;
import com.zenden.task_management_system.Classes.DTO.ReadTicketDTO;
import com.zenden.task_management_system.Services.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tickets")
@Slf4j
@Tag(name = "TicketController", description = "Контроллер для управления билетами")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Operation(summary = "Получить все билеты с пагинацией и сортировкой")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение списка билетов",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    @Transactional
    public ResponseEntity<Page<ReadTicketDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id") String sortBy) {
        log.info("Получение всех билетов, страница: {}, размер: {}, сортировка по: {}", page, size, sortBy);
        return ResponseEntity.ok(ticketService.getAllTickets(page, size, sortBy));
    }

    @Operation(summary = "Получить билет по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение билета",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReadTicketDTO.class))),
        @ApiResponse(responseCode = "404", description = "Билет не найден",
                     content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadTicketDTO> findById(@PathVariable long id) {
        log.info("Получение билета по ID: {}", id);
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @Operation(summary = "Создать новый билет")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Билет успешно создан",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReadTicketDTO.class)))
    })
    @PostMapping
    @Transactional
    public ResponseEntity<ReadTicketDTO> create(@RequestBody CreateEditUpdateTicketDTO entity) {
        log.info("Создание нового билета: {}", entity);
        return ResponseEntity.ok(ticketService.createTicket(entity));
    }

    @Operation(summary = "Обновить билет по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Билет успешно обновлен",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReadTicketDTO.class))),
        @ApiResponse(responseCode = "404", description = "Билет не найден",
                     content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReadTicketDTO> update(@PathVariable long id, @RequestBody CreateEditUpdateTicketDTO entity) {
        log.info("Обновление билета по ID: {}, данные: {}", id, entity);
        return ResponseEntity.ok(ticketService.updateTicket(id, entity));
    }

    @Operation(summary = "Удалить билет по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Билет успешно удален",
                     content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Билет не найден",
                     content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable long id) {
        log.info("Удаление билета по ID: {}", id);
        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Deleted");
    }
}