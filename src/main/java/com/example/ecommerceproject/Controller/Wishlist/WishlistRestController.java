package com.example.ecommerceproject.Controller.Wishlist;


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

    @PutMapping("/addProduct/{userId}/{productId}")
    public Wishlist addProduct(@PathVariable String userId,
                        @PathVariable String productId){
        System.out.println(userId);
        System.out.println(productId);
        return wishlistService.addProductToWishlist(Integer.parseInt(userId),
                Integer.parseInt(productId));
    }

    @PutMapping("/deleteProduct/{userId}")
    public Wishlist deleteProduct(@PathVariable int userId,
                               @RequestParam(value = "productId", required = true) int productId){
        return wishlistService.deleteProductFromWishlist(userId, productId);
    }

}
