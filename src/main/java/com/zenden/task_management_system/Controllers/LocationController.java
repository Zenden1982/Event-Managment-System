package com.zenden.task_management_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping("/all")
    public ResponseEntity<Page<LocationDTO>> getMethodName(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(locationService.getAllLocations(page, size));
    }

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<LocationDTO> create(@RequestBody LocationDTO entity) {
        
        locationService.createLocation(entity);
        return ResponseEntity.ok(entity);
    }
    
    
}
