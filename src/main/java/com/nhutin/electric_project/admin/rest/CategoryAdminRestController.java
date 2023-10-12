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

import com.nhutin.electric_project.model.Brand;
import com.nhutin.electric_project.model.Category;
import com.nhutin.electric_project.repository.categorysRepository;
import com.nhutin.electric_project.service.CategorysService;

@CrossOrigin("*")
@RestController
public class CategoryAdminRestController {
    @Autowired
    categorysRepository dmdao;

    @Autowired
    CategorysService categoryService;

    @PostMapping("/admin/create")
    public ResponseEntity<Category> Create(@RequestBody Category cate) {
        try {
            cate.setActive(true); // Thiết lập isActive cho item từ request thành true
            Category newCate = dmdao.save(cate); // Lưu item vào cơ sở dữ liệu
            return ResponseEntity.status(HttpStatus.CREATED).body(newCate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/admin/{categoryID}")
    public ResponseEntity<Category> update(@PathVariable int categoryID, @RequestBody Category updatedCate) {
        try {
            Category existingCate = dmdao.findById(categoryID);
            if (existingCate == null) {
                return ResponseEntity.notFound().build();
            }

            // Cập nhật thông tin từ updatedCate vào existingCate
            existingCate.setCategoryName(updatedCate.getCategoryName());
            existingCate.setActive(updatedCate.isActive() == true);

            Category savedCate = dmdao.save(existingCate);
            return ResponseEntity.ok(savedCate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/deleteCategory/{categoryID}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int categoryID) {
        try {
            Category existing = dmdao.findById(categoryID);
            if (existing == null) {
                return ResponseEntity.notFound().build();
            }
            existing.setActive(false); // Cập nhật trạng thái active về false
            Category deactivated = dmdao.save(existing);

            return ResponseEntity.ok(deactivated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
