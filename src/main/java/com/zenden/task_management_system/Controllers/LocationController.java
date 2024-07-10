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

import com.zenden.task_management_system.Classes.DTO.LocationDTO;
import com.zenden.task_management_system.Services.LocationService;




@RestController
@RequestMapping("/locations")
public class LocationController {
    
    @Autowired
    private LocationService locationService;

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(locationService.findById(id));
    }
    @Transactional
    @GetMapping()
    public ResponseEntity<Page<LocationDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(locationService.getAllLocations(page, size, sortBy));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<LocationDTO> create(@RequestBody LocationDTO entity) {
        
        locationService.createLocation(entity);
        return ResponseEntity.ok(entity);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<LocationDTO> putMethodName(@PathVariable long id,@RequestBody LocationDTO entity) {
        locationService.updateLocation(id, entity);
        
        return ResponseEntity.ok(entity);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMethodName(@PathVariable long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok("Deleted");
    }
    
    
}
