package com.nhutin.electric_project.service;



import com.fasterxml.jackson.databind.JsonNode;
import com.nhutin.electric_project.model.Order;

public interface OrdersService {
    public Order create(JsonNode orderData);

    public Order findById(Integer orderId);

    // public List<Order> findByUsername(String username);
}
