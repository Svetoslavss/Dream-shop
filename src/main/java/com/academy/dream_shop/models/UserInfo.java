package com.academy.dream_shop.models;


import com.academy.dream_shop.models.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_username", unique = true, nullable = false)
    private String username;

    @Size(min = 8, max = 20)
    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
            @ElementCollection(fetch = FetchType.EAGER)
            @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
            @Column(name = "role")
    List<Roles> roles;
}
