package com.zenden.task_management_system.Classes.Filters.Event;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.zenden.task_management_system.Classes.Event;

public class EventSpecifications {
    
    public static Specification<Event> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Event> descriptionLike(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Event> dateAfter(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("date"), date);
    }

    public static Specification<Event> dateBefore(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("date"), date);
    }

    public static Specification<Event> locationIdEquals(Long locationId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("location").get("id"), locationId);
    }

    public static Specification<Event> categoryIdEquals(Long categoryId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }
}
