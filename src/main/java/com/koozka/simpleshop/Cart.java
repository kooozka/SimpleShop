package com.koozka.simpleshop;

import com.koozka.simpleshop.model.Item;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Getter
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        getCartItemByItem(item).ifPresentOrElse(
                cartItem -> cartItem.increaseCounter(),
                () -> cartItems.add(new CartItem(item))
        );
        counter++;
        sum = sum.add(item.getPrice());
    }

    public void decreaseItem(Item item) {
        Optional<CartItem> oCartItem = getCartItemByItem(item);
        if (oCartItem.isPresent()) {
            CartItem cartItem = oCartItem.get();
            cartItem.decreaseCounter();
            if (cartItem.hasZeroItems()) {
                cartItems.removeIf(i -> i.getItem().getId() == item.getId());
            }
        }
        counter--;
        sum = sum.subtract(item.getPrice());
    }

    public void removeAllItems(Item item) {
        cartItems.removeIf(i -> i.getItem().getId() == item.getId());
        recalculateCart();
    }

    public void clearCart() {
        cartItems.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }

    private Optional<CartItem> getCartItemByItem(Item item) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.idEquals(item))
                .findFirst();
    }


    private void recalculateCart() {
        sum = cartItems.stream().map(cartItem -> cartItem.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        counter = cartItems.stream().mapToInt(cartItem -> cartItem.getCounter())
                .reduce(0, Integer::sum);
    }
}
