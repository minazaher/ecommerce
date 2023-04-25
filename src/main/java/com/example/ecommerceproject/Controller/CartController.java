package com.example.ecommerceproject.Controller;

import com.example.ecommerceproject.Model.Product;
import com.example.ecommerceproject.Model.User;
import com.example.ecommerceproject.Model.Order;
import com.example.ecommerceproject.Service.OrderService;
import com.example.ecommerceproject.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final OrderService orderService;
    @GetMapping
    public String viewCart(Model model, HttpSession session){
        Set<Product> cart = (Set<Product>) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/confirm")
    public String confirmCart(HttpSession session, Model model) {
        Set<Product> cart = (Set<Product>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        User user = (User) session.getAttribute("user");
        Order order = new Order();
        int orderPrice = 0;
        order.setUserId(user.getId());
        for (Product product : cart) {
            order.getProducts().add(product);
            orderPrice += product.getPrice();
        }
        order.setPrice(orderPrice);
        orderService.saveOrder(order);

        session.removeAttribute("cart");

        model.addAttribute("order", order);
        return "confirmation";
    }


}
