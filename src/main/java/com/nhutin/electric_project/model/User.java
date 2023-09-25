package com.nhutin.electric_project.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userID;

	@Column(name = "Address")
	private String address;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DateOfBirth")
	private Date dateOfBirth = new Date();

	@Column(name = "Email")
	private String email;

	@Column(name = "FullName")
	private String fullName;

	@Column(name = "Gender")
	private Boolean gender;

	@Column(name = "LockStatus")
	private Boolean lockStatus;

	@Column(name = "LoginPermission") // Quyền đăng nhập (true/false) => kích hoạt tk với token từ email
	private Boolean loginPermission;

	@Column(name = "Password")
	private String password;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "RegistrationDate")
	private Timestamp registrationDate;

	@Column(name = "Username")
	private String username;

	// New column - ROLE
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<Cart> carts;

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@OneToMany(mappedBy = "user")
	private List<Order> orders;

	@OneToMany(mappedBy = "user")
	private List<ProductReview> productReviews;

	@OneToMany(mappedBy = "user")
	private List<Shipping> shippings;

	@JsonIgnore
	@OneToOne(mappedBy = "user")
	private ConfirmationCode confirmationCode;

	public User(String username, String fullName, Boolean gender, Date dateOfBirth, String email, String phoneNumber,
			String password, Role role) {
		this.username = username;
		this.fullName = fullName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
	}

	public Integer getUserID() {
		return this.userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Boolean getLockStatus() {
		return this.lockStatus;
	}

	public void setLockStatus(Boolean lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Boolean getLoginPermission() {
		return this.loginPermission;
	}

	public void setLoginPermission(Boolean loginPermission) {
		this.loginPermission = loginPermission;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setUser(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setUser(null);

		return cart;
	}

	public ConfirmationCode getConfirmationCode() {
		return this.confirmationCode;
	}

	public void setConfirmationCode(ConfirmationCode confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}



//	@JsonIgnore
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
//		return Collections.singletonList(authority);
//	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return !lockStatus;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return loginPermission; // false được cho phép >< true không cho phép
	}

	public User(String address, Date dateOfBirth, String email, String fullName, Boolean lockStatus, String phoneNumber,
			String username, Role role) {
		super();
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.fullName = fullName;
		this.lockStatus = lockStatus;
		this.phoneNumber = phoneNumber;
		this.username = username;
		this.role = role;
	}

}