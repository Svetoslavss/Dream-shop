package com.academy.dream_shop.models.dto;

import com.academy.dream_shop.models.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String username;
    private String password;
    private String email;

    private List<Roles> roles;
}
