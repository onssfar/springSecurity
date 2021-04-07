package com.example.springsecurity.reposotory;

import com.example.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    org.springframework.security.core.userdetails.User findByUserName(String username);

}
