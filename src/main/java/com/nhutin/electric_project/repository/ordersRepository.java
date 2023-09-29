package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhutin.electric_project.model.Order;

public interface ordersRepository extends JpaRepository<Order, Integer>{
	@Query("SELECT o FROM Order o WHERE o.user LIKE user")
    List<Order> findOrdersByUser(@Param("user") Integer user);
}
