package com.zenden.task_management_system.Classes.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class ReadEventDTO {
    private long id;
    private String name;
    private String description;
    private Date date;
    private LocationDTO location;
    private CategoryDTO category;
    private Date createdAt;
    private Date updatedAt;
}
