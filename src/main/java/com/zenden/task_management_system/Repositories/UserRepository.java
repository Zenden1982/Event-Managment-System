package com.zenden.task_management_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenden.task_management_system.Classes.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
