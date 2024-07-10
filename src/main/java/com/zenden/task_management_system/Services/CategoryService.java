package com.zenden.task_management_system.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zenden.task_management_system.Classes.Category;
import com.zenden.task_management_system.Classes.DTO.CategoryDTO;
import com.zenden.task_management_system.Mapper.Mapper;
import com.zenden.task_management_system.Repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Mapper mapper;

    public Page<CategoryDTO> getAllCategories(int page, int size, String sortBy) {
        return categoryRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)) ).map(mapper::map);
    }

    public CategoryDTO getCategoryById(long id) {
        Optional<CategoryDTO> categoryDTO = categoryRepository.findById(id).map(mapper::map);
        return categoryDTO.orElse(null);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return mapper.map(categoryRepository.save(mapper.map(categoryDTO)));
    }

    public CategoryDTO updateCategory(long id, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            category.get().setName(categoryDTO.getName());
            return mapper.map(categoryRepository.save(category.get()));
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
