package com.nhutin.electric_project.user.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.model.User;
import com.nhutin.electric_project.repository.UserRepository;
import com.nhutin.electric_project.service.OrdersService;

@CrossOrigin("*")
@RestController
public class CheckoutRestcontroller {

  

  @Autowired
  UserRepository userDAO;

  @Autowired
  OrdersService orderService;

  @GetMapping("/rest/account")
  public ResponseEntity<User> getAccount(HttpSession session) {
    
  String email = (String) session.getAttribute("tenDangNhapLogin");
 
    if (email != null) {
      User user = userDAO.findByEmailLike(email);
      return ResponseEntity.ok(user);
    }
    return ResponseEntity.notFound().build();
  }
 


  @PostMapping("/rest/hoadon")
  public Order create(@RequestBody JsonNode orderData) {
    return orderService.create(orderData);
  }

}
