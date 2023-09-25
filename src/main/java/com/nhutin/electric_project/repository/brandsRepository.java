package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Brand;
import com.nhutin.electric_project.model.Product;

public interface brandsRepository extends JpaRepository<Brand, Integer> {
    // List<Product> findByBrandID(int brandID);
}
