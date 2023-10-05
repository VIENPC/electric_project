package com.nhutin.electric_project.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nhutin.electric_project.model.Supplier;
import com.nhutin.electric_project.repository.suppliersRepository;

@CrossOrigin("*")
@RestController
public class SupplierRestController {

    @Autowired
    suppliersRepository dmdao;

    @PostMapping("/admin/createSupplier")
    public ResponseEntity<Supplier> Create(@RequestBody Supplier supplier) {
        try {
            supplier.setActive(true); // Thiết lập isActive cho item từ request thành true
            Supplier newSupplier = dmdao.save(supplier); // Lưu item vào cơ sở dữ liệu
            return ResponseEntity.status(HttpStatus.CREATED).body(newSupplier);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/adminUpdateSupplier/{supplierID}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable int supplierID, @RequestBody Supplier update) {
        try {
            Supplier existing = dmdao.findById(supplierID);
            if (existing == null) {
                return ResponseEntity.notFound().build();
            }
            // Cập nhật thông tin từ update vào existing
            existing.setSupplierName(update.getSupplierName());
            existing.setEmail(update.getEmail());
            existing.setPhone(update.getPhone());
            existing.setAddress(update.getAddress());
            existing.setDescribe(update.getDescribe());
            existing.setWebsite(update.getWebsite());
            existing.setActive(update.isActive() == true);

            Supplier save = dmdao.save(existing);
            return ResponseEntity.ok(save);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/deleteSupplier/{supplierID}")
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable int supplierID) {
        try {
            Supplier existing = dmdao.findById(supplierID);
            if (existing == null) {
                return ResponseEntity.notFound().build();
            }
            existing.setActive(false); // Cập nhật trạng thái active về false
            Supplier deactivatedAccount = dmdao.save(existing);

            return ResponseEntity.ok(deactivatedAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
