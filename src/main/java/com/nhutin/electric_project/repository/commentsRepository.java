package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhutin.electric_project.model.Comment;
import com.nhutin.electric_project.model.Order;

public interface commentsRepository extends JpaRepository<Comment, Integer>{
	
    @Query("SELECT o FROM Comment o where o.product.productID= ?1")
	List<Comment> findByProduct(Integer product);

}
