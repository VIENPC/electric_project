package com.nhutin.electric_project.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Brand;
import com.nhutin.electric_project.model.Product;

public interface productsRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    Product findById(int productID);

    List<Product> findByCategory_CategoryIDAndProductIDNot(Integer categoryId, Integer currentItemId);

    List<Product> findByCategoryCategoryID(Integer categoryId);

    List<Product> findByBrandBrandID(Integer brandID);

    List<Product> findByproductNameContaining(String productName);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByPriceLessThanEqual(Double price);

    long countByActive(Boolean tt);
}
