package com.nhutin.electric_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int product_Id;
    private int category_id;
    private int brand_id;
    private String product_name;
    private Double price;
    private Data created_date;
    private String product_description;
    private String product_image;
    private int quantity;
    private boolean activities;
}
