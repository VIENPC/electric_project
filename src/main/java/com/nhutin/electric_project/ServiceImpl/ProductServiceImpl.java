package com.nhutin.electric_project.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhutin.electric_project.model.Brand;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.repository.brandsRepository;
import com.nhutin.electric_project.repository.productsRepository;
import com.nhutin.electric_project.service.ProductsService;

@Service
public class ProductServiceImpl implements ProductsService {
    @Autowired
    productsRepository productsRepository;

    @Autowired
    brandsRepository brandsRepository;

    public Product findById(Integer productID) {
        return productsRepository.findById(productID).get();
    }

    @Override
    public List<Product> getRelateditemsExcludingCurrent(Integer categoryID, Integer currentItemID) {
        return productsRepository.findByCategory_CategoryIDAndProductIDNot(categoryID, currentItemID);
    }

    // @Override
    // public List<Product> getProductsByBrand(Integer brandID) {
    // return productsRepository.findByBrandBrandID(brandID);
    // }

    // @Override
    // public List<Product> getProductsByCategory(Integer categoryID) {
    // return productsRepository.findByCategoryCategoryID(categoryID);
    // }

    @Override
    public List<Product> getProductsByPrice(Double price) {
        return productsRepository.findByPriceLessThanEqual(price);
    }

    @Override
    public List<Product> getProductsByCategory(int categoryID) {
        return productsRepository.findByCategoryCategoryID(categoryID);
    }

    @Override
    public List<Brand> getBrandsByCategory(int categoryID) {
        return brandsRepository.findByProducts_Category_CategoryID(categoryID);
    }
}
