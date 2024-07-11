package com.zenden.task_management_system.Classes.Filters.Location;

import org.springframework.data.jpa.domain.Specification;

import com.zenden.task_management_system.Classes.Location;

public class LocationSpecifications {

    public static Specification<Location> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Location> addressLike(String address) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("address"), "%" + address + "%");
    }

    public static Specification<Location> capacityMoreThanOrEqualTo(Integer capacity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("capacity"), capacity);
    }
}
