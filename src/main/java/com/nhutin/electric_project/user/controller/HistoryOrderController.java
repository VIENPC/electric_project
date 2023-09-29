package com.nhutin.electric_project.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.repository.ordersRepository;


@CrossOrigin("*")
@RestController
public class HistoryOrderController {
	
	@Autowired
	ordersRepository orderRepo;
     
	@GetMapping("/rest/history/{user}")
	public ResponseEntity<List<Order>> getOne(@PathVariable("user") int user) {
		if (!orderRepo.existsById(user)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderRepo.findOrdersByUser(user));
	}

}