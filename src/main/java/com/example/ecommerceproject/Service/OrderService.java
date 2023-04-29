package com.example.ecommerceproject.Service;

import com.example.ecommerceproject.Model.Order;
import com.example.ecommerceproject.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order getOrderById(Long id){
        return orderRepository.findOrderById(id);
    }
    public void saveOrder(Order order){
        orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAllWithProducts();
    }

}
