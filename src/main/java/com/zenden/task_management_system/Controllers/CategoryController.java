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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;




@RestController
@RequestMapping("/categories")
@Tag(name = "Категории", description = "Операции с категориями")
@Slf4j
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить категорию по id", description = "Получить категорию по id категории")
    public ResponseEntity<CategoryDTO> getById(@PathVariable long id) {
        log.info("Получение категории по id: {}", id);
        return ResponseEntity.ok((categoryService.getCategoryById(id)));
    }

    @GetMapping
    @Operation(summary = "Получить все категории", description = "Получить все категории")
    public ResponseEntity<Page<CategoryDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        log.info("Получение всех категорий: page={}, size={}, sortedBy={}", page, size, sortBy);
        return ResponseEntity.ok(categoryService.getAllCategories(page, size, sortBy));
    }

    @PostMapping
    @Operation(summary = "Создать категорию", description = "Создать категорию")
    public ResponseEntity<CategoryDTO> postMethodName(@RequestBody CategoryDTO entity) {
        log.info("Создание категории: {}", entity);
        return ResponseEntity.ok(categoryService.createCategory(entity));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить категорию", description = "Обновить категорию по id категории")
    public ResponseEntity<CategoryDTO> putMethodName(@PathVariable long id, @RequestBody CategoryDTO entity) {
        log.info("Обновление категории по id: {}, данные: {}", id, entity);
        return ResponseEntity.ok(categoryService.updateCategory(id, entity));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить категорию", description = "Удалить категорию по id категории")
    public ResponseEntity<String> deleteMethodName(@PathVariable long id) {
        log.info("Удаление категории по id: {}", id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Успешно удалено");
    }
}
