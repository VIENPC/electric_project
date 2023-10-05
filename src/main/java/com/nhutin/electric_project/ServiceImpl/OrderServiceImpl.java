package com.nhutin.electric_project.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhutin.electric_project.model.Order;
import com.nhutin.electric_project.model.OrderDetail;
import com.nhutin.electric_project.model.Product;
import com.nhutin.electric_project.repository.orderDetailsRepository;
import com.nhutin.electric_project.repository.ordersRepository;
import com.nhutin.electric_project.repository.productsRepository;
import com.nhutin.electric_project.service.OrdersService;


@Service
public class OrderServiceImpl implements OrdersService {
	@Autowired
	ordersRepository dao;

	@Autowired
	productsRepository spdao;

	@Autowired
	orderDetailsRepository ddao;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);

		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
				.peek(d -> d.setOrder(order)).collect(Collectors.toList());
		for (OrderDetail detail : details) {
			
			// Lấy mã sản phẩm từ hóa đơn chi tiết
			Integer masp = detail.getProduct().getProductID();

			// Lấy số lượng sản phẩm từ hóa đơn chi tiết
			int quantity = detail.getQuantity();

			// Tìm sản phẩm trong cơ sở dữ liệu bằng mã sản phẩm
			Product product = spdao.findById(masp).get(); 

			// Cập nhật số lượng mới vào sản phẩm trong cơ sở dữ liệu
			if (product != null) {
				// Lấy số lượng hiện có của sản phẩm trong cơ sở dữ liệu
				int currentQuantity = product.getQuantity();
				
				// Trừ số lượng sản phẩm đã thêm vào hóa đơn chi tiết từ số lượng hiện có
				int newQuantity = currentQuantity - quantity;

				// Cập nhật số lượng mới vào sản phẩm trong cơ sở dữ liệu
				product.setQuantity(newQuantity);
				// Lưu sản phẩm sau khi đã cập nhật số lượng mới
				spdao.save(product);
			} else {
				  throw new IllegalArgumentException("Không tìm thấy sản phẩm");
			}
		}

		ddao.saveAll(details);

		return order;

	}

	@Override
	public Order findById(Integer mahd) {
		return dao.findById(mahd).get();
	}

	// @Override
	// public List<Order> findByUsername(String username) {
	// 	return dao.findByUsername(username);
	// }
}
