package com.nhutin.electric_project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "`promotions`")
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "discount_percent")
	private Integer discountPercent;

	@Column(name = "min_order_amount")
	private Integer minOrderAmount;

	@OneToMany(mappedBy = "promotion")
	private List<Promotion_Historie> promotionHistories;

	@Temporal(TemporalType.TIME)
	@Column(name = "dates")
	private Date usedDates;

	@Column(name = "status")
	private boolean status;

}
