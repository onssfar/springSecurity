package com.example.springsecurity;

import com.example.springsecurity.entity.User;
import com.example.springsecurity.reposotory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication

public class SpringSecurityApplication extends SpringBootServletInitializer {
    @Autowired
    private UserRepo repository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(1, "ons", "password", "ons@gmail.com"),
                new User(2, "user1", "pwd1", "user1@gmail.com"),
                new User(3, "user2", "pwd2", "user2@gmail.com"),
                new User(4, "user3", "pwd3", "user3@gmail.com")

        ).collect(Collectors.toList());
        repository.saveAll(users);
    }

    @Bean
    public WebMvcConfigurer corsConfiqurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*").allowCredentials(true);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
