package com.zenden.task_management_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.Location;
import com.zenden.task_management_system.Classes.DTO.LocationDTO;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.LocationRepository;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private Mapper mapper;

    public LocationDTO findById(long id) {
        return mapper.map(locationRepository.findById(id).get());
    }

    public Page<LocationDTO> getAllLocations(int page, int size) {
        return locationRepository.findAll(Pageable.ofSize(size).withPage(page)).map(mapper::map);
    }

    public Location createLocation(LocationDTO location) {
        return locationRepository.save(mapper.map(location));
    }
}
