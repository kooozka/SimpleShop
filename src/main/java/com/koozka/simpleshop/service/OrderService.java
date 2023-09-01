package com.koozka.simpleshop.service;

import com.koozka.simpleshop.Cart;
import com.koozka.simpleshop.dto.OrderDto;
import com.koozka.simpleshop.mapper.OrderMapper;
import com.koozka.simpleshop.model.order.Order;
import com.koozka.simpleshop.model.order.OrderItem;
import com.koozka.simpleshop.repository.order.OrderItemRepository;
import com.koozka.simpleshop.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));
        cart.clearCart();
    }
}
