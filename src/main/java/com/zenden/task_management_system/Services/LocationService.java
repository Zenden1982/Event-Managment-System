package com.zenden.task_management_system.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.DTO.LocationDTO;
import com.zenden.task_management_system.Classes.Filters.Location.LocationFilter;
import com.zenden.task_management_system.Classes.Filters.Location.LocationSpecifications;
import com.zenden.task_management_system.Classes.Location;
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

    public Page<LocationDTO> getAllLocationsByFilter(int page, int size, String sortBy, LocationFilter filter) {
        Specification<Location> spec = Specification.where(null);
        if (filter.getName() != null) {
            spec = spec.and(LocationSpecifications.nameLike(filter.getName()));
        }
        if (filter.getAddress() != null) {
            spec = spec.and(LocationSpecifications.addressLike(filter.getAddress()));
        }
        if (filter.getCapacity() != null) {
            spec = spec.and(LocationSpecifications.capacityMoreThanOrEqualTo(filter.getCapacity()));
        }

        return locationRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortBy))).map(mapper::map);
    }

    public Page<LocationDTO> getAllLocations(int page, int size, String sortBy) {
        return locationRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy))).map(mapper::map);
    }

    public Location createLocation(LocationDTO location) {
        return locationRepository.save(mapper.map(location));
    }

    public Location updateLocation(Long id,LocationDTO location) {
        Optional<Location> location2 = locationRepository.findById(id);
        if (location2.isPresent()) {
            location2.get().setName(location.getName());
            location2.get().setAddress(location.getAddress());
            location2.get().setCapacity(location.getCapacity());
            return locationRepository.save(location2.get());
        }
        else {
            throw new RuntimeException("Location not found");
        }
    }

    public void deleteLocation(long id) {
        locationRepository.deleteById(id);
    }
}
