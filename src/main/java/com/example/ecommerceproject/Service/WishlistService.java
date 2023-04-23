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


//    public Wishlist createUserWishlist(int userid){
//        User user = userRepository.findById(userid).orElseThrow();
//        Wishlist wishlist = user.getWishlist();
//        wishlist.setUser(user);
//        return wishlist;
//    }

    public Wishlist addProductToWishlist(int userid) {
        User user = userRepository.findById(userid).orElseThrow();
        Product product = productRepository.findById(25L).orElseThrow();
        Wishlist wishlist = user.getWishlist();

        wishlist.getProducts().add(product);

        wishlistRepository.save(wishlist);
        return wishlist;
    }


}