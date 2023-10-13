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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "`promotion_histories`")
@NoArgsConstructor
@AllArgsConstructor
public class Promotion_Historie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "discount_amount")
	private Float discountAmount;

	@ManyToOne
	@JoinColumn(name = "promotion_id")
	private Promotion promotion;

	@Temporal(TemporalType.TIME)
	@Column(name = "date_user")
	private Date usedDate;

	@Column(name = "status")
	private boolean status;

}