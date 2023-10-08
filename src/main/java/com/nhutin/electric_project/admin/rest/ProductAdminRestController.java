package com.nhutin.electric_project.admin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhutin.electric_project.model.Product;

import com.nhutin.electric_project.repository.productsRepository;
import com.nhutin.electric_project.service.ProductsService;

@CrossOrigin("*")
@RestController
public class ProductAdminRestController {
    @Autowired
    productsRepository dmdao;
    @Autowired
    ProductsService ProductService;

    @PostMapping("/admin/createProduct")
    public ResponseEntity<Product> Create(@RequestBody Product product) {
        try {
            product.setActive(true);
            Product newProduct = dmdao.save(product); // Lưu item vào cơ sở dữ liệu
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/adminUpdateProduct/{productID}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productID,
            @RequestBody Product update) {
        try {
            Product existing = dmdao.findById(productID);
            if (existing == null) {
                return ResponseEntity.notFound().build();
            }
            // Cập nhật thông tin từ update vào existing
            existing.setProductName(update.getProductName());
            existing.setPrice(update.getPrice());
            existing.setCategory(update.getCategory());
            existing.setBrand(update.getBrand());
            existing.setConfiguration(update.getConfiguration());
            existing.setDescription(update.getDescription());
            existing.setQuantity(update.getQuantity());
            existing.setSupplier(update.getSupplier());
            existing.setImage(update.getImage());
            existing.setActive(update.isActive() == true);

            Product save = dmdao.save(existing);
            return ResponseEntity.ok(save);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/deleteProduct/{productID}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int productID) {
        try {
            Product existing = dmdao.findById(productID);
            if (existing == null) {
                return ResponseEntity.notFound().build();
            }
            existing.setActive(false); // Cập nhật trạng thái active về false
            Product deactivatedAccount = dmdao.save(existing);

            return ResponseEntity.ok(deactivatedAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
