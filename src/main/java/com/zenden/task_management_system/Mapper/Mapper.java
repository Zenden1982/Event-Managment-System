package com.zenden.task_management_system.Mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.zenden.task_management_system.Classes.Category;
import com.zenden.task_management_system.Classes.Event;
import com.zenden.task_management_system.Classes.Location;
import com.zenden.task_management_system.Classes.DTO.CategoryDTO;
import com.zenden.task_management_system.Classes.DTO.CreateEditUpdateEventDTO;
import com.zenden.task_management_system.Repositories.CategoryRepository;
import com.zenden.task_management_system.Repositories.LocationRepository;

public class Mapper {
    
    @Autowired
    private LocationRepository locationRepository;
    private CategoryRepository categoryRepository;
    
    public CategoryDTO map(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public Category map(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }

    public CreateEditUpdateEventDTO map(Event event) {
        CreateEditUpdateEventDTO eventDTO = new CreateEditUpdateEventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocationId(event.getLocation().getId());
        eventDTO.setDate(event.getDate());
        eventDTO.setCategoryId(event.getCategory().getId());
        return eventDTO;
    }

    public Event map(CreateEditUpdateEventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(locationRepository.findById(eventDTO.getLocationId()).orElseGet(() -> new Location()));
        event.setCategory(categoryRepository.findById(eventDTO.getCategoryId()).orElseGet(() -> new Category()));
        event.setDate(eventDTO.getDate());
        return event;
    }
}
