package com.nhutin.electric_project.user.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.repository.productsRepository;
import com.nhutin.electric_project.service.ProductsService;

@CrossOrigin("*")
@RestController
public class ProductRestController {
    @Autowired
    productsRepository productdao;

    @Autowired
    ProductsService productService;

    @Autowired
    UserRepository userDAO;

    

    @GetMapping("rest/product")
    public List<Product> findAll() {
        return productdao.findAll();
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

    @GetMapping("/rest/products-by-category")
    public List<Product> getProductsByCategory(@RequestParam Integer categoryID) {
        // Gọi phương thức của Repository để lấy danh sách sản phẩm theo brandID
        List<Product> products = productService.getProductsByCategory(categoryID);
        return products;
    }

    @GetMapping("/rest/product/{masp}")
    public ResponseEntity<Product> getOne(@PathVariable("masp") Integer masp) {
        if (!productdao.existsById(masp)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productdao.findById(masp).get());
    }

    
}
