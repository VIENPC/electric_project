package com.nhutin.electric_project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shippings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transport_id")
	private Integer transportID;

	private String address;

	private String district;

	private String note;

	@Column(name="reciplient_name")
	private String reciplientName;

	private Boolean state;

	@OneToOne
	@JoinColumn(name="transport_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}