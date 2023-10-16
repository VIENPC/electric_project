package com.nhutin.electric_project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.statustt = 1")
    Double sumTotalAmountOfApprovedOrders();

    // Thống kê biểu đồ
    @Query(nativeQuery = true, value = "SELECT t.thang, COALESCE(SUM(o.total_amount), 0) AS doanh_thu_thang " +
            "FROM (SELECT 1 AS thang " +
            "      UNION SELECT 2 " +
            "      UNION SELECT 3 " +
            "      UNION SELECT 4 " +
            "      UNION SELECT 5 " +
            "      UNION SELECT 6 " +
            "      UNION SELECT 7 " +
            "      UNION SELECT 8 " +
            "      UNION SELECT 9 " +
            "      UNION SELECT 10 " +
            "      UNION SELECT 11 " +
            "      UNION SELECT 12) t " +
            "LEFT JOIN orders o ON MONTH(o.order_date) = t.thang " +
            "                  AND o.statushd = 4 " +
            "                  AND o.statustt = 1 " +
            "                  AND o.order_date >= DATEADD(MONTH, -12, GETDATE()) " +
            "GROUP BY t.thang " +
            "ORDER BY t.thang")
    List<Object[]> calculateRevenueByMonth();

    @Query("SELECT o.orderId AS orderId, o.name AS customerName, p.productName AS productName, od.quantity AS quantity,p.price, (p.price * od.quantity) AS totalPrice "
            +
            "FROM Order o " +
            "INNER JOIN o.orderDetails od " +
            "INNER JOIN od.product p " +
            "WHERE o.orderDate BETWEEN :startDate AND :endDate " +
            "AND o.statushd = 4")
    List<Object[]> thongKeDonHang(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    // @Query("SELECT o FROM Order o where o.statushd= ?1")
    // List<Order> findHdTt(Integer tthd);

    @Query("SELECT o FROM Order o where o.user.userID= ?1")
    List<Order> findHistory(Integer user);

    @Query("SELECT CONCAT(DATEDIFF(MINUTE, o.orderDate, CURRENT_TIMESTAMP), ' ') AS ThoiGianDaTao FROM Order o WHERE o.orderId = ?1")
    Integer findThoiGianDaTao(Integer orderId);

}
