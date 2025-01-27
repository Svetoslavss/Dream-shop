package com.academy.dream_shop.services.product.auth;


import com.academy.dream_shop.exceptions.UserNotFoundException;
import com.academy.dream_shop.models.UserInfo;
import com.academy.dream_shop.models.dto.LoginDto;
import com.academy.dream_shop.models.dto.RegisterDto;
import com.academy.dream_shop.models.enums.Roles;
import com.academy.dream_shop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    private UserInfo register(RegisterDto register){
        logger.info("hit register service: {}", register);
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(register.getUsername());
        userInfo.setPassword(register.getPassword());
        userInfo.setEmail(register.getEmail());
        List<Roles> roles = List.of(Roles.USER);
        userInfo.setRoles(roles);
        return userRepository.save(userInfo);
    }

    private LoginDto login(LoginDto loginDto){
        try {
            logger.info("hit login service: {}", loginDto);
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            logger.info("Principal: {}", authentication.getPrincipal());

            UserInfo user = userRepository.findUserByUsername(loginDto.getUsername());

            if (user == null) {
                throw new UserNotFoundException("User not found.");
            }

            return new LoginDto(user.getUsername(), user.getPassword(), user.getEmail(), List.of(Roles.USER, Roles.ADMIN));
        } catch (UserNotFoundException | BadCredentialsException e){
            logger.info("error login", e);
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
