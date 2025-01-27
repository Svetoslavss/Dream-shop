package com.academy.dream_shop.controller;


import com.academy.dream_shop.services.product.auth.AuthService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import java.util.function.ToDoubleBiFunction;

@RestController
@CrossOrigin
@NoArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    public void register(){};

    public void login(){};
}
