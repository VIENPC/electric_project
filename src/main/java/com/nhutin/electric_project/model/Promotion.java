package com.nhutin.electric_project.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@Column(name = "date") // Không sử dụng Temporal và chỉ cần sử dụng LocalDateTime
	private LocalDateTime usedDates;

	@Column(name = "status")
	private boolean status;

	@Column(name = "date_end") // Không sử dụng Temporal và chỉ cần sử dụng LocalDateTime
	private LocalDateTime dateEnd;
}
