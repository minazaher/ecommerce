package com.example.ecommerceproject.Controller.Wishlist;

import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Service.WishlistService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    @GetMapping("/wishlist")
    public String wishlist(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        Set<Product> products = wishlistService.getUserWishlist(user.getId()).getProducts();
        model.addAttribute("products", products);
        return "wishlist";
    }
//    @GetMapping("/MyWishlist")
//    public String wishlist( Model model, User user) {
//        user = (User) session.getAttribute("user");
//        System.out.println(user);
//        Set<Product> products = wishlistService.getUserWishlist(user.getId()).getProducts();
//        model.addAttribute("products", products);
//        return "wishlist";
//    }

}
