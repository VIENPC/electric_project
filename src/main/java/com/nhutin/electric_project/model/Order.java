package com.nhutin.electric_project.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer orderId;

	@Temporal(TemporalType.DATE)
	@Column(name = "order_date")
	private Date orderDate = new Date();

	@Column(name="total_amount")
	private Double totalAmount;

	@OneToMany(mappedBy="order")
	private List<OrderDetail> orderDetails;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy="order")
	private List<Payment> payments;

	@OneToOne(mappedBy="order")
	private Shipping shipping;
}