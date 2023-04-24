package com.example.ecommerceproject.Controller;


import com.example.ecommerceproject.Model.Wishlist;
import com.example.ecommerceproject.Service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/wishlist")
@RequiredArgsConstructor
public class WishlistRestController {

    private final WishlistService wishlistService;

    @GetMapping("/{userId}")
    public Wishlist getUserWishlist(@PathVariable int userId){
        return wishlistService.getUserWishlist(userId);
    }

    @PostMapping("/create")
    public Wishlist create( @RequestParam(value = "userId", required = true) int userid){
        return wishlistService.createUserWishlist(userid);
    }

    @PutMapping("/addProduct/{userId}")
    public Wishlist addProduct(@PathVariable int userId,
                        @RequestParam(value = "productId", required = true) int productId){
        return wishlistService.addProductToWishlist(userId, productId);
    }

    @PutMapping("/deleteProduct/{userId}")
    public Wishlist deleteProduct(@PathVariable int userId,
                               @RequestParam(value = "productId", required = true) int productId){
        return wishlistService.deleteProductFromWishlist(userId, productId);
    }

}
