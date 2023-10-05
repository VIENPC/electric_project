package com.nhutin.electric_project.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.repository.productsRepository;
import com.nhutin.electric_project.service.ProductsService;

@Service
public class ProductServiceImpl implements ProductsService {
    @Autowired
    productsRepository productsRepository;

    public Product findById(Integer productID) {
        return productsRepository.findById(productID).get();
    }

    @Override
    public List<Product> getRelateditemsExcludingCurrent(Integer categoryId, Integer currentItemId) {
        return productsRepository.findByCategory_CategoryIDAndProductIDNot(categoryId, currentItemId);
    }

    @Override
    public List<Product> getProductsByBrand(Integer brandID) {
        return productsRepository.findByBrandBrandID(brandID);
    }

    @Override
    public List<Product> getProductsByCategory(Integer categoryID) {
        return productsRepository.findByCategoryCategoryID(categoryID);
    }

    @Override
    public List<Product> getProductsByPrice(Double price) {
        return productsRepository.findByPriceLessThanEqual(price);
    }

}
