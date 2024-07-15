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

import com.zenden.task_management_system.Classes.DTO.ParticipantDTO;
import com.zenden.task_management_system.Services.ParticipantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/participants")
@Slf4j
@Tag(name = "ParticipantController", description = "Контроллер для управления участниками")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @Operation(summary = "Получить всех участников с пагинацией и сортировкой")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение списка участников",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    @Transactional
    public ResponseEntity<Page<ParticipantDTO>> getParticipants(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size,
                                                                @RequestParam(defaultValue = "id") String sortBy) {
        log.info("Получение всех участников, страница: {}, размер: {}, сортировка по: {}", page, size, sortBy);
        return ResponseEntity.ok(participantService.getAllParticipants(page, size, sortBy));
    }

    @Operation(summary = "Получить участника по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение участника",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantDTO.class))),
        @ApiResponse(responseCode = "404", description = "Участник не найден",
                     content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ParticipantDTO> getParticipant(@PathVariable Long id) {
        log.info("Получение участника по ID: {}", id);
        return ResponseEntity.ok(participantService.getParticipantById(id));
    }

    @Operation(summary = "Создать нового участника")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Участник успешно создан",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantDTO.class)))
    })
    @PostMapping
    @Transactional
    public ResponseEntity<ParticipantDTO> createParticipant(@RequestBody ParticipantDTO participant) {
        log.info("Создание нового участника: {}", participant);
        return ResponseEntity.ok(participantService.createParticipant(participant));
    }

    @Operation(summary = "Обновить участника по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Участник успешно обновлен",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantDTO.class))),
        @ApiResponse(responseCode = "404", description = "Участник не найден",
                     content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ParticipantDTO> updateParticipant(@PathVariable Long id, @RequestBody ParticipantDTO participant) {
        log.info("Обновление участника по ID: {}, данные: {}", id, participant);
        return ResponseEntity.ok(participantService.updateParticipant(id, participant));
    }

    @Operation(summary = "Удалить участника по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Участник успешно удален",
                     content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Участник не найден",
                     content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteParticipant(@PathVariable Long id) {
        log.info("Удаление участника по ID: {}", id);
        participantService.deleteParticipant(id);
        return ResponseEntity.ok("Deleted");
    }
}
