package com.zenden.task_management_system.Classes.DTO;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReadEventDTO {
    private long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private LocationDTO location;
    private CategoryDTO category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
