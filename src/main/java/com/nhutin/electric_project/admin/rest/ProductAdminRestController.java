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

import com.nhutin.electric_project.repository.brandsRepository;
import com.nhutin.electric_project.repository.productsRepository;
import com.nhutin.electric_project.service.BrandsService;

@CrossOrigin("*")
@RestController
public class ProductAdminRestController {
    @Autowired
    productsRepository dmdao;
    @Autowired
    BrandsService brandService;

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

    // @PutMapping("/adminUpdateBrand/{brandID}")
    // public ResponseEntity<Brand> updateBrand(@PathVariable int brandID,
    // @RequestBody Brand update) {
    // try {
    // Brand existing = dmdao.findById(brandID);
    // if (existing == null) {
    // return ResponseEntity.notFound().build();
    // }
    // // Cập nhật thông tin từ update vào existing
    // existing.setBrandName(update.getBrandName());
    // existing.setImage(update.getImage());
    // existing.setActive(update.isActive() == true);

    // Brand save = dmdao.save(existing);
    // return ResponseEntity.ok(save);
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    // }
    // }

    // @PutMapping("/deleteBrand/{brandID}")
    // public ResponseEntity<Brand> deleteBrand(@PathVariable int brandID) {
    // try {
    // Brand existing = dmdao.findById(brandID);
    // if (existing == null) {
    // return ResponseEntity.notFound().build();
    // }
    // existing.setActive(false); // Cập nhật trạng thái active về false
    // Brand deactivatedAccount = dmdao.save(existing);

    // return ResponseEntity.ok(deactivatedAccount);
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    // }
    // }
}
