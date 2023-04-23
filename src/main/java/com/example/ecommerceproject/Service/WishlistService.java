package com.example.ecommerceproject.Service;

import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Model.Wishlist;
import com.example.ecommerceproject.Model.Product;
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


    public Wishlist createUserWishlist(int userId){
        User user = userRepository.findById(userId).orElseThrow() ;
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlistRepository.save(wishlist);
        return wishlist;
    }

    public Wishlist addProductToWishlist(int userId, int productId) {
        Product product = productRepository.findById((long) productId).orElseThrow();
        Wishlist wishlist = wishlistRepository.findByUser(userId).orElseThrow();
        wishlist.getProducts().add(product);
        wishlistRepository.save(wishlist);
        return wishlist;
    }

    public Wishlist deleteProductFromWishlist(int userId, int productId) {
        Wishlist wishlist = wishlistRepository.findByUser(userId).orElseThrow();
        Product product = productRepository.findById((long) productId).orElseThrow();
        wishlist.getProducts().remove(product);
        wishlistRepository.save(wishlist);
        return wishlist;
    }

    public Wishlist getUserWishlist(int userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Wishlist wishlist = wishlistRepository.findByUser(userId).orElse(new Wishlist(user));
        wishlistRepository.save(wishlist);
        return wishlist;
    }
}