package com.example.ecommerceproject.Controller;

import com.example.ecommerceproject.Model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String viewCart(Model model, HttpSession session){
        Set<Product> cart = (Set<Product>) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "cart";
    }


}
