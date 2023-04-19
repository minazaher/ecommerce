package com.example.ecommerceproject.Repository;

import com.example.ecommerceproject.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from orders o where o.orderId =:id")
    Order getOrderById(Long id);

    @Query("SELECT o FROM orders o LEFT JOIN FETCH o.products")
    List<Order> findAllWithProducts();
}
