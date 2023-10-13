package com.nhutin.electric_project.user.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhutin.electric_project.model.CartDetail;
import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.model.detailcart;
import com.nhutin.electric_project.repository.productsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CartRestController {

    @Autowired
    productsRepository prDAO;

    @PostMapping("/rest/cartdetail")
   public ResponseEntity<String> saveCartDetails(@RequestBody List<detailcart> cartDetails) {
        try {
            
            // Lặp qua danh sách cartDetails và lưu dữ liệu vào cơ sở dữ liệu
            for(detailcart dt: cartDetails){
                System.out.println(dt.getQty());
                 System.out.println(dt.getProducutId());
              
            }
           
            return ResponseEntity.ok("Dữ liệu đã được lưu vào cơ sở dữ liệu.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Lỗi khi lưu dữ liệu vào cơ sở dữ liệu: " + e.getMessage());
        }
    }
    
}
