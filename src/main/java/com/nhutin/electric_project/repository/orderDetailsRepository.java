package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhutin.electric_project.model.OrderDetail;

public interface orderDetailsRepository extends JpaRepository<OrderDetail, Integer> {
	
    @Query("SELECT od FROM OrderDetail od WHERE od.order.orderId = :orderId")
    List<OrderDetail> findOrderDetailsByOrderId(Integer orderId);
}
