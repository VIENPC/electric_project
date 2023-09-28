package com.nhutin.electric_project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhutin.electric_project.model.Order;

public interface ordersRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT od.order_detailId, o.name, od.quantity, p.productName, od.price " +
            "FROM OrderDetail od " +
            "JOIN od.order o " +
            "JOIN od.product p " +
            "WHERE o.orderId = ?1")
    List<Object[]> findOrderDetailsByOrderId(Integer orderId);

    @Query("SELECT o FROM Order o where o.statushd= ?1")
    List<Order> findHdTt(Integer tthd);

    @Query("SELECT COUNT(o) FROM Order o where o.statushd= ?1")
    Integer countDontt(Integer tthd);

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.statushd = 1")
    Double sumTotalAmountOfApprovedOrders();

}
