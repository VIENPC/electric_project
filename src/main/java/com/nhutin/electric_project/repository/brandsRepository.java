package com.nhutin.electric_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhutin.electric_project.model.Brand;
public interface brandsRepository extends JpaRepository<Brand, Integer> {
    
        @Query("SELECT sum(od.quantity),  sum(o.totalAmount) FROM Brand b join Product p on b.brandID = p.brand.brandID join OrderDetail od on od.product.productID = p.productID join Order o on o.orderId = od.order.orderId where o.statushd = 4 and b.brandID = ?1 AND MONTH(o.orderDate) = ?2 ")
   Object[] sosanhaang(Integer mahang, Integer thang);
    @Query("SELECT sum(p.quantity) FROM Brand b join Product p on b.brandID = p.brand.brandID where b.brandID = ?1 ")
    Object[] sptkth(Integer mahang);
    
    
    
    
    
    
    Brand findById(int brandID);

    List<Brand> findByProducts_Category_CategoryID(int categoryID);

    @Query(value = "WITH AllMonths AS ( " +
            "SELECT 1 AS thang UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 " +
            "UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 " +
            "UNION SELECT 10 UNION SELECT 11 UNION SELECT 12 " +
            ") " +
            "SELECT t.thang, COALESCE(SUM(CASE WHEN o.order_id IS NOT NULL THEN p.price * od.quantity ELSE 0 END), 0) AS doanh_thu_thang "
            +
            "FROM AllMonths t " +
            "LEFT JOIN orders o ON MONTH(o.order_date) = t.thang " +
            "LEFT JOIN order_details od ON od.order_id = o.order_id " +
            "LEFT JOIN products p ON od.product_id = p.product_id " +
            "LEFT JOIN brands b ON b.brand_id = p.brand_id " +
            "WHERE (o.order_date >= DATEADD(MONTH, -12, GETDATE()) OR o.order_date IS NULL) " +
            "AND o.statushd = 4 " +
            "AND (b.brand_id = :brand_id OR b.brand_id IS NULL) " +
            "GROUP BY t.thang " +
            "ORDER BY t.thang", nativeQuery = true)
    List<Object[]> getRevenueByMonth(@Param("brand_id") int brandId);

}
