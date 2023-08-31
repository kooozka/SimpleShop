package com.koozka.simpleshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.koozka.simpleshop.service.CartService;

@Controller
public class HomeController {
    private final CartService cartService;

    @Autowired
    public HomeController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", cartService.getAllItems());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Integer itemId, Model model) {
        cartService.addItemToCart(itemId);
        model.addAttribute("items", cartService.getAllItems());
        return "home";
    }
}
