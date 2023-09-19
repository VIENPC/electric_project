package com.nhutin.electric_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product_reviews {
    private int review_id;
    private int product_id;
    private String user_id;
    private int rating;
    private String review_text;
    private String review_data;
}
