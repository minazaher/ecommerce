package com.example.ecommerceproject.Service;

import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Model.Wishlist;
import com.example.ecommerceproject.Repository.UserRepository;
import com.example.ecommerceproject.Repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;


//    public Wishlist getWishlist(int id) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            Wishlist wishlist = user.getWishlist();
//            if (wishlist == null) {
//                wishlist = new Wishlist();
//                wishlist.setId(userId);
//                user.setWishlist(wishlist);
//                userRepository.save(user);
//            }
//            return wishlist;
//        } else {
//            throw new UsernameNotFoundException("User not found with id: " + userId);
//        }
//    }


    public Wishlist getUserWishlist(int userId){
        Wishlist wishlist = wishlistRepository.findByUserId(userId);
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setUser(userRepository.findById(userId).orElseThrow());
            wishlist = wishlistRepository.save(wishlist);
        }
        return wishlist;
    }

    public void addProductToWishlist(int userId, Product product){
        Wishlist wishlist = getUserWishlist(userId);
        wishlist.getProductSet().add(product);
        product.getWishlists().add(wishlist);
        wishlistRepository.save(wishlist);
    }


}
