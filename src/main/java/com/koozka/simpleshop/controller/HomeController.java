package com.koozka.simpleshop.controller;

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
import java.util.Optional;

@Controller
public class HomeController {
    private final ItemRepository itemRepository;

    @Autowired
    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") int itemId, Model model, HttpSession session) {
        List<Item> cart = getSessionCart(session);
        itemRepository.findById(itemId)
                .ifPresent(item -> {
                    cart.add(item);
                    session.setAttribute("cart", cart);
                });
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }

    private List<Item> getSessionCart(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }
}
