package com.example.ecommerceproject.Repository;

import com.example.ecommerceproject.Model.Wishlist;
import com.example.ecommerceproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Query("select w from Wishlist w where w.user.id =:userid")
    Optional<Wishlist> findByUser(int userid);

}
