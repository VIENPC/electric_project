package com.nhutin.electric_project.user.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    HttpSession session;
  

  @Autowired
  UserRepository userDAO;

  @Autowired
  OrdersService orderService;

  @GetMapping("/rest/account")
    public ResponseEntity<User> getAccount() {
        String email = (String) session.getAttribute("tenDangNhapLogin");
       User account = userDAO.findByEmailLike(email);
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }

  @PostMapping("/rest/hoadon")
  public Order create(@RequestBody JsonNode orderData) {
    return orderService.create(orderData);
  }

}
