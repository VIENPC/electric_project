package com.nhutin.electric_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nhutin.electric_project.model.Product;

@Service
public interface ProductsService {
    Product findById(Integer productID);

    // Tìm các sản phẩm cùng loại với một productID cụ thể
    List<Product> getRelateditemsExcludingCurrent(Integer categoryId, Integer currentItemId);

    List<Product> getProductsByBrand(Integer brandID);

    List<Product> getProductsByCategory(Integer categoryID);
}
