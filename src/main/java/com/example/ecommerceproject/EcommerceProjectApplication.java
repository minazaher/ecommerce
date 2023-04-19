package com.example.ecommerceproject;

import com.example.ecommerceproject.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;

@SpringBootApplication
public class EcommerceProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceProjectApplication.class, args);
    }
}
