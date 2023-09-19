package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhutin.electric_project.model.Cart;

public interface cartsRepository extends JpaRepository<Cart, Integer>{

}
