package com.zenden.task_management_system.Classes.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class CreateEditUpdateEventDTO {

    private Long id;
    private String name;
    private String description;
    private Date date;
    private Long locationId;
    private Long categoryId;
}
