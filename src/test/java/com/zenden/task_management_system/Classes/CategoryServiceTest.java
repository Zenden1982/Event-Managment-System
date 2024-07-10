package com.zenden.task_management_system.Classes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.zenden.task_management_system.Classes.DTO.CategoryDTO;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.CategoryRepository;
import com.zenden.task_management_system.Services.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Test Category");

        categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test Category");
    }

    @Test
    void testGetAllCategories() {
        Page<Category> categories = new PageImpl<>(Arrays.asList(category));
        when(categoryRepository.findAll(PageRequest.of(0, 10, Sort.by("name")))).thenReturn(categories);
        when(mapper.map(any(Category.class))).thenReturn(categoryDTO);

        Page<CategoryDTO> result = categoryService.getAllCategories(0, 10, "name");

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetCategoryById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(mapper.map(any(Category.class))).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.getCategoryById(1L);

        assertNotNull(result);
        assertEquals("Test Category", result.getName());
    }

    @Test
    void testCreateCategory() {
        when(mapper.map(any(CategoryDTO.class))).thenReturn(category);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(mapper.map(any(Category.class))).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.createCategory(categoryDTO);

        assertNotNull(result);
        assertEquals("Test Category", result.getName());
    }

    @Test
    void testUpdateCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(mapper.map(any(Category.class))).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.updateCategory(1L, categoryDTO);

        assertNotNull(result);
        assertEquals("Test Category", result.getName());
    }

    @Test
    void testUpdateCategoryNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            categoryService.updateCategory(1L, categoryDTO);
        });
    }

    @Test
    void testDeleteCategory() {
        categoryService.deleteCategory(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
