package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Brand;

public interface brandsRepository extends JpaRepository<Brand, Integer> {
    Brand findById(int brandID);

    List<Brand> findByProducts_Category_CategoryID(int categoryID);
}
