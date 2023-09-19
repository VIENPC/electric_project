package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Order;

public interface ordersRepository extends JpaRepository<Order, Integer>{

}
