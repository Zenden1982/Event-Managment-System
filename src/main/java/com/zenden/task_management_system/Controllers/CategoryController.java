package com.zenden.task_management_system.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zenden.task_management_system.Classes.DTO.CategoryDTO;
import com.zenden.task_management_system.Services.CategoryService;

import jakarta.transaction.Transactional;




@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;


    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable long id) {
        return ResponseEntity.ok((categoryService.getCategoryById(id)));
    }

    @Transactional
    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(categoryService.getAllCategories(page, size, sortBy));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CategoryDTO> postMethodName(@RequestBody CategoryDTO entity) {
        
        
        return ResponseEntity.ok(categoryService.createCategory(entity));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> putMethodName(@PathVariable long id, @RequestBody CategoryDTO entity) {
        return ResponseEntity.ok(categoryService.updateCategory(id, entity));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMethodName(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted");
    }
    
    
}
