package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Category;
import com.nhutin.electric_project.model.Product;

public interface categorysRepository extends JpaRepository<Category, Integer> {
    // List<Product> findByCategoryID(Integer categoryID);
    Category findById(int categoryID);
}
