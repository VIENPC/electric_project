package com.nhutin.electric_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cartdetail {
    private int cartdetail_id;
    private int product_id;
    private int cart_id;
    private int quantity;
}
