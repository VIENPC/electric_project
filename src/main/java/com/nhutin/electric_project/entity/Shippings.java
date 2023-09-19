package com.nhutin.electric_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shippings {
    private int transport_id;
    private String user_id;
    private String reciplient_name;
    private boolean state;
    private String district;
    private String address;
    private String note;
}
