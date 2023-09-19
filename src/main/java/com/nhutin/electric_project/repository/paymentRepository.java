package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Payment;

public interface paymentRepository extends JpaRepository<Payment, Integer>{

}
