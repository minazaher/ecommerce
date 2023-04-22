package com.example.ecommerceproject.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany(mappedBy = "products")
    private Set<Wishlist> wishlists ;
}
