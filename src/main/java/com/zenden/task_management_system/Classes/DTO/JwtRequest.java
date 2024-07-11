package com.zenden.task_management_system.Classes.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
