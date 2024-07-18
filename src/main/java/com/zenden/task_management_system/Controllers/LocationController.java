package com.zenden.task_management_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.zenden.task_management_system.Classes.DTO.LocationDTO;
import com.zenden.task_management_system.Classes.Filters.Location.LocationFilter;
import com.zenden.task_management_system.Services.LocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;




@RestController
@RequestMapping("/locations")
@Slf4j
@Tag(name = "LocationController", description = "Контроллер для управления местоположениями")
public class LocationController {
    
    @Autowired
    private LocationService locationService;

    @Operation(summary = "Получить местоположение по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение местоположения", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationDTO.class))),
        @ApiResponse(responseCode = "404", description = "Местоположение не найдено", 
                     content = @Content(mediaType = "application/json"))
    })
    @Transactional
    @GetMapping("/{id}")
    public LocationDTO findById(@PathVariable long id) {
        log.info("Получение местоположения по ID: {}", id);
        return locationService.findById(id);
    }

    @Operation(summary = "Получить все местоположения по фильтру с пагинацией и сортировкой")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение отфильтрованного списка местоположений", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @Transactional
    @PostMapping("/filter")
    public ResponseEntity<Page<LocationDTO>> findAllByFilter(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "id") String sortBy,
                                                             @RequestBody LocationFilter filter) {
        log.info("Получение всех местоположений по фильтру, страница: {}, размер: {}, сортировка по: {}, фильтр: {}", page, size, sortBy, filter);
        return ResponseEntity.ok(locationService.getAllLocationsByFilter(page, size, sortBy, filter));
    }

    @Operation(summary = "Получить все местоположения с пагинацией и сортировкой")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение списка местоположений", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    @Transactional
    public ResponseEntity<Page<LocationDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy) {
        log.info("Получение всех местоположений, страница: {}, размер: {}, сортировка по: {}", page, size, sortBy);
        return ResponseEntity.ok(locationService.getAllLocations(page, size, sortBy));
    }

    @Operation(summary = "Создать новое местоположение")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Местоположение успешно создано", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationDTO.class)))
    })
    @Transactional
    @PostMapping
    public ResponseEntity<LocationDTO> create(@RequestBody LocationDTO entity, @RequestParam ("file") MultipartFile file) {
        log.info("Создание нового местоположения: {}", entity);
        try {
            locationService.createLocation(entity, file);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(entity);
    }

    @Operation(summary = "Обновить местоположение по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Местоположение успешно обновлено", 
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationDTO.class))),
        @ApiResponse(responseCode = "404", description = "Местоположение не найдено", 
                     content = @Content(mediaType = "application/json"))
    })
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> update(@PathVariable long id, @RequestBody LocationDTO entity) {
        log.info("Обновление местоположения по ID: {}, данные: {}", id, entity);
        locationService.updateLocation(id, entity);
        return ResponseEntity.ok(entity);
    }

    @Operation(summary = "Удалить местоположение по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Местоположение успешно удалено", 
                     content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Местоположение не найдено", 
                     content = @Content(mediaType = "application/json"))
    })
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        log.info("Удаление местоположения по ID: {}", id);
        locationService.deleteLocation(id);
        return ResponseEntity.ok("Deleted");
    }
}
