package com.nhutin.electric_project.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhutin.electric_project.model.Product;

public interface productsRepository extends JpaRepository<Product, Integer> {
        List<Product> findAll();

        Product findById(int productID);

        List<Product> findByCategory_CategoryIDAndProductIDNot(Integer categoryId, Integer currentItemId);

        List<Product> findByCategoryCategoryID(Integer categoryId);

        List<Product> findByBrandBrandID(Integer brandID);

        List<Product> findByproductNameContaining(String productName);

        List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

        long countByActive(Boolean tt);

        // sản phẩm bán chạy nhất

        @Query("SELECT p.productID, p.productName, p.price, SUM(od.quantity) AS totalSold, p.brand.brandName, p.image "
                        +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "GROUP BY p.productID, p.productName, p.price, p.brand.brandName, p.image " +
                        "ORDER BY totalSold DESC")
        List<Object[]> findBestSellingProducts();

        // sản phẩm bán tệ nhất
        @Query("SELECT p.productID, p.productName, p.price, SUM(od.quantity) AS totalSold, p.brand.brandName, p.image "
                        +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "GROUP BY p.productID, p.productName, p.price, p.brand.brandName, p.image " +
                        "ORDER BY totalSold ASC")
        List<Object[]> findLostSellingProducts();

        // sản phẩm bán chạy theo tháng
        @Query("SELECT p.productID, p.productName, SUM(od.quantity) AS totalQuantity, p.price AS unitPrice, SUM(od.quantity * p.price) AS totalSales, b.brandName, o.orderDate, p.image "
                        +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "INNER JOIN p.brand b " +
                        "INNER JOIN od.order o " +
                        "WHERE o.statushd = 4 " +
                        "AND o.statustt = true " +
                        "AND MONTH(o.orderDate) = :month " +
                        "GROUP BY p.productID, p.productName, p.price, b.brandName, o.orderDate, p.image " +
                        "ORDER BY totalQuantity DESC")
        List<Object[]> findBestSellingProductsMonth(Integer month);

        // sản phẩm bán chạy theo Ngày
        @Query("SELECT p.productID, p.productName, SUM(od.quantity) AS totalQuantity, " +
                        "p.price AS unitPrice, SUM(od.quantity * p.price) AS total_price, " +
                        "MIN(o.orderDate) AS orderDate, b.brandName AS brandName,p.image " +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "INNER JOIN od.order o " +
                        "INNER JOIN p.brand b " +
                        "WHERE o.statushd = 4 AND o.statustt = 1 AND " +
                        "CONVERT(DATE, o.orderDate) = :selectedDate " + // Sử dụng tham số :selectedDate
                        "GROUP BY p.productID, p.productName, p.price, b.brandName,p.image " +
                        "ORDER BY totalQuantity DESC")
        List<Object[]> findProductsSoldOnDate(@Param("selectedDate") Date selectedDate);

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
        
        @Query("SELECT p.productID, p.productName, sum(o.quantity), p.price, (sum(o.quantity) * p.price), b.brandName FROM Product p JOIN Category c ON c.categoryID =p.category.categoryID JOIN OrderDetail o ON o.product.productID = p.productID JOIN Order od ON od.orderId = o.order.orderId JOIN Brand b ON b.brandID = p.brand.brandID Where od.statushd = 4 AND c.categoryID =?1 GROUP BY p.productID, p.productName, p.price, b.brandName")
        List<Object[]> thongkeSanPhamTheoMuc(int muc);

     

        List<Product> findByActive(Boolean active);


        @Query("SELECT p.productID, p.productName, p.price, od.quantity, p.brand.brandName, SUM(od.quantity) AS totalSold, p.image "
                        +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "GROUP BY p.productID, p.productName, p.price, od.quantity, p.brand.brandName, p.image " +
                        "ORDER BY totalSold DESC")
        List<Object[]> findBestSellingProducts();

        // sản phẩm bán chạy theo tháng
        @Query("SELECT p.productID, p.productName, SUM(od.quantity) AS totalQuantity, p.price AS unitPrice, SUM(od.quantity * p.price) AS totalSales, b.brandName, o.orderDate, p.image "
                        +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "INNER JOIN p.brand b " +
                        "INNER JOIN od.order o " +
                        "WHERE o.statushd = 4 " +
                        "AND o.statustt = true " +
                        "AND MONTH(o.orderDate) = :month " +
                        "GROUP BY p.productID, p.productName, p.price, b.brandName, o.orderDate, p.image " +
                        "ORDER BY totalQuantity DESC")
        List<Object[]> findBestSellingProductsMonth(Integer month);

        // sản phẩm bán chạy theo Ngày
        @Query("SELECT p.productID, p.productName, SUM(od.quantity) AS totalQuantity, " +
                        "p.price AS unitPrice, SUM(od.quantity * p.price) AS total_price, " +
                        "MIN(o.orderDate) AS orderDate, b.brandName AS brandName,p.image " +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "INNER JOIN od.order o " +
                        "INNER JOIN p.brand b " +
                        "WHERE o.statushd = 4 AND o.statustt = 1 AND " +
                        "CONVERT(DATE, o.orderDate) = :selectedDate " + // Sử dụng tham số :selectedDate
                        "GROUP BY p.productID, p.productName, p.price, b.brandName,p.image " +
                        "ORDER BY totalQuantity DESC")
        List<Object[]> findProductsSoldOnDate(@Param("selectedDate") Date selectedDate);

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


        @Query("SELECT p.productID AS masp, p.productName AS tensp, SUM(od.quantity) AS soluong, p.price AS giasp, " +
                        "SUM(od.quantity * p.price) AS totalPrice, p.brand.brandName AS tenhang " +
                        "FROM OrderDetail od " +
                        "INNER JOIN od.product p " +
                        "WHERE od.order.orderDate BETWEEN :startDate AND :endDate " +
                        "AND od.order.statushd = 4 " +
                        "GROUP BY p.productID, p.productName, p.price, p.brand.brandName")
        List<Object[]> thongkesptg(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


        List<Product> findByPriceLessThanEqual(Double price);



}
