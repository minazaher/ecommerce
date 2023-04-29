package com.example.ecommerceproject.Controller;

import com.example.ecommerceproject.Model.Order;
import com.example.ecommerceproject.Repository.OrderRepository;
import com.example.ecommerceproject.Service.OrderService;
import com.example.ecommerceproject.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @RequestMapping("/")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @RequestMapping("/cart")
    public String getCart(){
        return "cart";
    }
    @RequestMapping("/tracking-order")
    public String trackOrder(){
        return "tracking-order";
    }
    @RequestMapping("/confirmation")
    public String confirmOrder(){
        return "confirmation";
    }
    @RequestMapping("/checkout")
    public String checkout(){
        return "checkout";
    }
    @PostMapping("/save")
    public void saveOrder(@RequestBody Order order){
        orderRepository.save(order);
    }
}
