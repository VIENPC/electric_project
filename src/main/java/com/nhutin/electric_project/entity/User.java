package com.nhutin.electric_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String user_id;
    private String fullname;
    private String password;
    private String phone;
    private String address;
    private String email;
    private boolean role;
    private boolean active;
}
