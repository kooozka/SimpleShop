package com.koozka.simpleshop.repository.order;

import com.koozka.simpleshop.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
