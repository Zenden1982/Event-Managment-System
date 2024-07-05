package com.zenden.task_management_system.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zenden.task_management_system.Classes.Category;

@Component
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
