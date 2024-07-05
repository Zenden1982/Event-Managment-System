package com.zenden.task_management_system.Classes.DTO;

import com.zenden.task_management_system.Classes.Role;

import lombok.Value;

@Value
public class UserDTO {
    private long id;
    private String username;
    private String email;
    private String password;
    private Role role;
}
