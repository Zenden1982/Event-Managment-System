package com.zenden.task_management_system.Classes.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateEditUpdateEventDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Long locationId;
    private Long categoryId;
}
