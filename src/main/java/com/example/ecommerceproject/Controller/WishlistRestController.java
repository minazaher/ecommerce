package com.example.ecommerceproject.Controller;

import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Model.Wishlist;
import com.example.ecommerceproject.Repository.WishlistRepository;
import com.example.ecommerceproject.Service.ProductService;
import com.example.ecommerceproject.Service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistRestController {

    private final WishlistService wishlistService;
    private final ProductService productService;
    private final WishlistRepository wishlistRepository;

    @PostMapping("/addProduct")
    public void addProductToWishlist(){
        Product product = productService.getProductsById(12L);
        wishlistService.addProductToWishlist(12,product);
    }

    @GetMapping("/get")
    public Wishlist getUserWishlist(){
        return wishlistService.getUserWishlist(12);
    }


}
