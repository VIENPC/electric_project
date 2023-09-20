package com.nhutin.electric_project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private String userID;

	private boolean active;

	private String address;

	private String email;

	private String fullname;

	private String password;

	private Integer phone;

	private Boolean role;

	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	@OneToMany(mappedBy="user")
	private List<Cart> carts;

	@OneToMany(mappedBy="user")
	private List<Comment> comments;

	@OneToMany(mappedBy="user")
	private List<Order> orders;

	@OneToMany(mappedBy="user")
	private List<ProductReview> productReviews;

	@OneToMany(mappedBy="user")
	private List<Shipping> shippings;
}