package com.example.ecommerceproject.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "products")
public class Product {
    @Id
    private Long id;
    private String title;
    private String description;
    private int price;
    private int stock;
    private String brand;
    private String category;
    private String thumbnail;
}
