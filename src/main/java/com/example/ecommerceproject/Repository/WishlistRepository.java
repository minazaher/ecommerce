package com.example.ecommerceproject.Repository;

import com.example.ecommerceproject.Model.Order;
import com.example.ecommerceproject.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Query("SELECT w FROM wishlists w left join FETCH w.productSet")
    List<Wishlist> findAllWithProducts();

    Wishlist findByUserId(int userId);
}
