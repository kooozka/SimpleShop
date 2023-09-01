package com.koozka.simpleshop.repository.order;

import com.koozka.simpleshop.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
