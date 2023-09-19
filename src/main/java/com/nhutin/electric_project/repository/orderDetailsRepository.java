package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.OrderDetail;

public interface orderDetailsRepository extends JpaRepository<OrderDetail, Integer>{

}
