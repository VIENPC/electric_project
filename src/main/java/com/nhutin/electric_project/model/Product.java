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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productID;

	private Boolean active;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate = new Date();

	private String description;

	private String image;

	private Double price;

	@Column(name = "product_name")
	private String productName;

	private Integer quantity;

	@OneToMany(mappedBy = "product")
	private List<CartDetail> cartDetails;

	@OneToMany(mappedBy = "product")
	private List<Comment> comments;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "product")
	private List<ProductReview> productReviews;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
}