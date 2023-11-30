package com.nhutin.electric_project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    @NotBlank(message = "Hãy nhập tên người dùng")
    @Pattern(message = "Sai định dạng tên người dùng", regexp = "^[a-zA-Z0-9_-]{3,16}$")
    @Column(length = 50)
    private final String username;
    @NotBlank(message = "Hãy nhập tên người dùng")
    @Column(length = 100)
    private final String fullname;
    @NotNull(message = "Hãy chọn giới tính")
    private final Boolean gender;
    @NotNull(message = "Hãy nhập ngày sinh")
    @Column(length = 100)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Date birthday;
    @NotBlank(message = "Hãy nhập email")
    @Email(message = "Email không không đúng định dạng")
    @Column(length = 100)
    private final String email;
    @NotBlank(message = "Hãy nhập số điện thoại")
    @Pattern(message = "Sai định dạng số điện thoại", regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$")
    @Column(length = 20)
    private final String phonenumber;
    @NotEmpty(message = "Hãy nhập mật khẩu")
    @Pattern(message = "Mật khẩu ít nhất 6 ký tự và bao gồm 1 ký tự đặc biệt", regexp = "^(?=.*[@#$%^&+=!]).{6,}$")
    @Column(length = 100)
    private final String password;
    @NotEmpty(message = "Hãy nhập mật khẩu xác nhận")
    @Column(length = 100)
    private final String confirmPassword;
    @Column(length = 100)
    private final String address;

}
