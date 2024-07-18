package com.zenden.task_management_system.Classes.DTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class LocationDTO {

    private long id;
    private String name;
    private String address;
    private Integer capacity;
    private MultipartFile image;
}
