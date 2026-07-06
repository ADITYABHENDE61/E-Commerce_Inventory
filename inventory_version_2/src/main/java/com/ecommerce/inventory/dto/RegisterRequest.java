package com.ecommerce.inventory.dto;

import com.ecommerce.inventory.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
}