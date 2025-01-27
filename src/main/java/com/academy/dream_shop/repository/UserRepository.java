package com.academy.dream_shop.repository;

import com.academy.dream_shop.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findUserByUsername(String username);
}
