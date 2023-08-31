package com.koozka.simpleshop;

import com.koozka.simpleshop.model.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        boolean notFound = true;
        for (CartItem ci : cartItems) {
            if (ci.getItem().getId() == item.getId()) {
                notFound = false;
                ci.increaseCounter();
                break;
            }
        }

        if (notFound) {
            cartItems.add(new CartItem(item));
        }
    }

    public void removeItem(Item item) {
        for (CartItem ci : cartItems) {
            if (ci.getItem().getId() == item.getId()) {
                ci.decreaseCounter();
                if (ci.hasZeroItems()) {
                    cartItems.remove(ci);
                }
                break;
            }
        }
    }
}
