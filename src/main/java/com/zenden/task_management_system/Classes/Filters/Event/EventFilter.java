package com.zenden.task_management_system.Classes.Filters.Event;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventFilter {
    private String name;
    private String description;
    private LocalDateTime date;
    private Long locationId;
    private Long categoryId;
}
