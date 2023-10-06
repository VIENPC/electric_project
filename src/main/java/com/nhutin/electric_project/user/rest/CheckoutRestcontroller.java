package com.nhutin.electric_project.user.rest;

import javax.servlet.http.HttpServletRequest;
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
import com.nhutin.electric_project.config.CookieUtils;
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
  CookieUtils cook;

  @Autowired
  UserRepository userDAO;

  @Autowired
  OrdersService orderService;

  @GetMapping("/rest/account")
  public ResponseEntity<User> getAccount(HttpServletRequest req) {
    String email = cook.get("tenDangNhapCookie", req);

    User kh = userDAO.findByEmailLike(email);
    if (kh != null) {
      return ResponseEntity.ok(kh);
    }
    return ResponseEntity.notFound().build();

  }

  @PostMapping("/rest/hoadon")
  public Order create(@RequestBody JsonNode orderData) {
    return orderService.create(orderData);
  }

}
