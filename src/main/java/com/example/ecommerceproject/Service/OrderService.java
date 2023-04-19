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

    public Order findOrderById(Long id){
        return orderRepository.getOrderById(id);
    }

    public List<Order> findAllOrders(){
        return orderRepository.findAllWithProducts();
    }

}
