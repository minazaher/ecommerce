package com.example.ecommerceproject.Service;

import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Model.Wishlist;
import com.example.ecommerceproject.Repository.ProductRepository;
import com.example.ecommerceproject.Repository.UserRepository;
import com.example.ecommerceproject.Repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public Wishlist getUserWishlist(){
        User user = userRepository.findById(19).orElseThrow();
        if (user.getWishlist() == null){
            Wishlist wishlist = new Wishlist();
            user.setWishlist(wishlist);
            wishlistRepository.save(wishlist);
            userRepository.save(user);
        }
        return user.getWishlist();
    }
}