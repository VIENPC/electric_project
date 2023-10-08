package com.nhutin.electric_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhutin.electric_project.model.Cart;

public interface cartsRepository extends JpaRepository<Cart, Integer>{
    @Query("SELECT o FROM Cart o where o.user.userID =?1")
    Cart findByUserID(Integer makh);
}
