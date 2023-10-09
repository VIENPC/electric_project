package com.nhutin.electric_project.user.rest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.model.Supplier;
import com.nhutin.electric_project.repository.productsRepository;
import com.nhutin.electric_project.repository.suppliersRepository;
import com.nhutin.electric_project.service.ProductsService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
public class ProductRestController {
    @Autowired
    productsRepository productdao;

    @Autowired
    suppliersRepository suppliersRepository;

    @Autowired
    ProductsService productService;

    @GetMapping("rest/product")
    public List<Product> findAllProduct() {
        return productdao.findAll();
    }

    @GetMapping("rest/supplier")
    public List<Supplier> findAll() {
        return suppliersRepository.findAll();
    }

    @GetMapping("/rest/detail/{productID}")
    public ResponseEntity<Map<String, Object>> getItemDetails(@PathVariable("productID") int productID) {
        Product product = productService.findById(productID);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        List<Product> relatedProducts = productService.getRelateditemsExcludingCurrent(
                product.getCategory().getCategoryID(), productID);
        Map<String, Object> response = new HashMap<>();
        response.put("product", product);
        response.put("relatedProducts", relatedProducts);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rest/products-by-brand")
    public List<Product> getProductsByBrand(@RequestParam Integer brandID) {
        // Gọi phương thức của Repository để lấy danh sách sản phẩm theo brandID
        List<Product> products = productService.getProductsByBrand(brandID);
        return products;
    }

    @GetMapping("/rest/products-by-brand/{brandID}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable("brandID") int brandID) {
        // Gọi phương thức của Repository để lấy danh sách sản phẩm theo brandID
        List<Product> products = productService.getProductsByBrand(brandID);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/rest/products-by-category/{categoryID}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("categoryID") int categoryID) {
        // Gọi phương thức của Repository để lấy danh sách sản phẩm theo brandID
        List<Product> products = productService.getProductsByCategory(categoryID);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/rest/product-search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam(name = "productName") String productName) {
        try {
            List<Product> products = productdao.findByproductNameContaining(productName);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/rest/filterByPrice")
    public ResponseEntity<List<Product>> filterItemsByPrice(
            @RequestParam(name = "minPrice") BigDecimal minPrice,
            @RequestParam(name = "maxPrice") BigDecimal maxPrice) {
        try {
            List<Product> filteredItems = productdao.findByPriceBetween(minPrice, maxPrice);
            return ResponseEntity.ok(filteredItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/rest/products-by-price")
    public ResponseEntity<List<Product>> getProductsByPrice(@RequestParam("price") Double price) {
        // Gọi phương thức của Repository để lấy danh sách sản phẩm theo giá
        List<Product> products = productService.getProductsByPrice(price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/rest/product/{masp}")
    public ResponseEntity<Product> getOne(@PathVariable("masp") Integer masp) {
        if (!productdao.existsById(masp)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productdao.findById(masp).get());
    }

}
