package com.example.ecommerceproject.Model;

import com.example.ecommerceproject.Repository.WishlistRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "wishlists")
public class Wishlist {

    @Id
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> productSet = new HashSet<>();

    public void addProduct(Product product) {
        productSet.add(product);
        product.getWishlists().add(this);
    }

    public void removeProduct(Product product) {
        productSet.remove(product);
        product.getWishlists().remove(this);
    }


}
