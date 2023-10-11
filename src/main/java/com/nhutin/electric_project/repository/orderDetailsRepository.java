package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhutin.electric_project.model.OrderDetail;

public interface orderDetailsRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT od FROM OrderDetail od WHERE od.order.orderId = :orderId")
    List<OrderDetail> findOrderDetailsByOrderId(Integer orderId);

    @Query("SELECT od.product.productID, SUM(od.quantity) AS totalQuantity " +
            "FROM OrderDetail od " +
            "WHERE od.order.user.userID = :user " +
            "GROUP BY od.product.productID " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findMostOrderedProductByUserId(@Param("user") Integer user);
}
