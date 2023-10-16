package com.nhutin.electric_project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventorys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "inventory_id")
  private Integer inventoryID;

  @Column(name = "quantity")
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

}
