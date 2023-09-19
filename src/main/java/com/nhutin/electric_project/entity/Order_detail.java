package com.nhutin.electric_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order_detail {
    private int order_detailId;
    private int order_id;
    private int product_id;
    private int quantity;
    private float price;
}
