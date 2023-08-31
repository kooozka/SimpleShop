package com.koozka.simpleshop.service;

import com.koozka.simpleshop.Cart;
import com.koozka.simpleshop.model.Item;
import com.koozka.simpleshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final ItemRepository itemRepository;
    private final Cart cart;

    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void addItemToCart(Integer itemId) {
        itemRepository.findById(itemId)
                .ifPresent(item -> cart.addItem(item));
    }

    public void decreaseItem(Integer itemId) {
        itemRepository.findById(itemId)
                .ifPresent(item -> cart.decreaseItem(item));
    }

    public void removeItem(Integer itemId) {
        itemRepository.findById(itemId)
                .ifPresent(item -> cart.removeAllItems(item));
    }


}
