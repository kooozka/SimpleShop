package com.koozka.simpleshop.controller;

import com.koozka.simpleshop.Cart;
import com.koozka.simpleshop.model.Item;
import com.koozka.simpleshop.repository.ItemRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final ItemRepository itemRepository;
    private final Cart cart;

    @Autowired
    public HomeController(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") int itemId, Model model) {
        itemRepository.findById(itemId)
                .ifPresent(item -> cart.addItem(item));
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }
}
