package com.koozka.simpleshop.model.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;
    private int orderId;
    private int itemId;
    private int amount;

    public OrderItem(int orderId, int itemId, int amount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.amount = amount;
    }
}
