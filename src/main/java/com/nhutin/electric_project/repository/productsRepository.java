package com.nhutin.electric_project.repository;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhutin.electric_project.model.Product;

public interface productsRepository extends JpaRepository<Product, Integer> {
        List<Product> findAll();

        List<Product> findByCategory_CategoryIDAndProductIDNot(Integer categoryId, Integer currentItemId);

        List<Product> findByCategoryCategoryID(Integer categoryId);

        List<Product> findByBrandBrandID(Integer brandID);

        List<Product> findByproductNameContaining(String productName);

        List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

        long countByActive(Boolean tt);

        @Query("SELECT p.productID, p.productName, p.price, od.quantity, p.brand.brandName, SUM(od.quantity) AS totalSold "
                        +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "GROUP BY p.productID, p.productName, p.price, od.quantity, p.brand.brandName " +
                        "HAVING SUM(od.quantity) > 5")
        List<Object[]> findBestSellingProducts();

        @Query("SELECT p.productID, p.productName, p.quantity, SUM(od.quantity) AS totalSold, p.price, b.brandName " +
                        "FROM Order o " +
                        "JOIN o.orderDetails od " +
                        "JOIN od.product p " +
                        "JOIN p.brand b " +
                        "WHERE b.brandID = ?1 " +
                        "GROUP BY p.productID, p.productName, p.quantity, p.price, b.brandName")
        List<Object[]> thongkesph(Integer mahang);

        @Query("SELECT p.productID, p.productName, od.quantity AS quantity, p.price AS unitPrice, (od.quantity * p.price) AS totalPrice, p.brand.brandName AS brandName "
                        +
                        "FROM OrderDetail od " +
                        "JOIN Product p ON od.product.productID = p.productID " +
                        "JOIN Brand b ON p.brand.brandID = b.brandID " +
                        "JOIN Order o ON od.order.orderId = o.orderId " +
                        "WHERE o.statushd = 4 AND b.brandID = ?1")
        List<Object[]> thongkeSanPhamTheoBrand(int brandId);

        List<Product> findByActive(Boolean active);

}
