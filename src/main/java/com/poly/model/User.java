package com.poly.model;

import com.poly.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String phone;
    private Date birthday;
    private boolean gender;
    private String avatar;
    private Role role;
    private boolean status;
    private Date createdAt;
}
