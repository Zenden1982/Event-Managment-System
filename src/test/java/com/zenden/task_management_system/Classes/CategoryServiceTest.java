package com.zenden.task_management_system.Classes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.zenden.task_management_system.Classes.DTO.CategoryDTO;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.CategoryRepository;
import com.zenden.task_management_system.Services.CategoryService;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        Category category = new Category();
        category.setId(1L);
        category.setName("TestCategory");

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("TestCategory");

        List<Category> categories = Arrays.asList(category);
        Page<Category> page = new PageImpl<>(categories);
        when(categoryRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(mapper.map(any(Category.class))).thenReturn(categoryDTO);

        Page<CategoryDTO> result = categoryService.getAllCategories(0, 1, "name");
        assertEquals(1, result.getTotalElements());
        assertEquals("TestCategory", result.getContent().get(0).getName());
    }

    @Test
    void testGetCategoryById() {
        Category category = new Category();
        category.setId(1L);
        category.setName("TestCategory");

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("TestCategory");

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(mapper.map(any(Category.class))).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.getCategoryById(1L);
        assertNotNull(result);
        assertEquals("TestCategory", result.getName());
    }

    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setName("TestCategory");

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("TestCategory");

        when(mapper.map(any(CategoryDTO.class))).thenReturn(category);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(mapper.map(any(Category.class))).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.createCategory(categoryDTO);
        assertNotNull(result);
        assertEquals("TestCategory", result.getName());
    }

    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("TestCategory");

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("UpdatedCategory");

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(mapper.map(any(Category.class))).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.updateCategory(1L, categoryDTO);
        assertNotNull(result);
        assertEquals("UpdatedCategory", result.getName());
    }

    @Test
    void testDeleteCategory() {
        doNothing().when(categoryRepository).deleteById(anyLong());
        categoryService.deleteCategory(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
