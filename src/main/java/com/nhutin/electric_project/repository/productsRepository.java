package com.nhutin.electric_project.repository;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Product;

public interface productsRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    List<Product> findByCategory_CategoryIDAndProductIDNot(Integer categoryId, Integer currentItemId);

    List<Product> findByCategoryCategoryID(Integer categoryId);

    List<Product> findByBrandBrandID(Integer brandID);
}
