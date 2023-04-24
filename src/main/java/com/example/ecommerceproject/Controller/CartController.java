package com.example.ecommerceproject.Controller;

import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController{
    private final ProductService productService;

    @PostMapping("/add/{productId}")
    public void addProductToCart(HttpSession session,@PathVariable String productId){
        Set<Product> cart = (Set<Product>) session.getAttribute("cart");
        if (cart == null){
            cart = new HashSet<>();
            session.setAttribute("cart", cart);
        }
        Long product_id = Long.parseLong(productId);
        Product product = productService.getProductsById(product_id);
        cart.add(product);
        System.out.println(cart.toString());
    }
}
