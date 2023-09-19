package com.nhutin.electric_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Suppliers {
    private int supplier_id;
    private String supplier_name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String describe;

}
