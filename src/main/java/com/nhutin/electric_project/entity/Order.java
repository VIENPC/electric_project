package com.nhutin.electric_project.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int order_id;
    private String user_id;
    private Date order_date;
    private float total_amount;

}
