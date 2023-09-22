package com.nhutin.electric_project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cartdetail_id")
	private Integer cartdetailID;

	private Integer quantity;

	@ManyToOne
	@JoinColumn(name="cart_id")
	private Cart cart;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
}