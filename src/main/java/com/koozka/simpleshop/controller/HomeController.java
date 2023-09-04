package com.koozka.simpleshop.controller;

import com.koozka.simpleshop.model.SportDiscipline;
import com.koozka.simpleshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.koozka.simpleshop.service.CartService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final CartService cartService;
    private final ItemRepository itemRepository;
    private SportDiscipline selectedDiscipline;

    @Autowired
    public HomeController(CartService cartService, ItemRepository itemRepository) {
        this.cartService = cartService;
        this.itemRepository = itemRepository;
        selectedDiscipline = null;
    }

    @GetMapping("/")
    public String home(Model model) {
        selectedDiscipline = null;
        model.addAttribute("items", cartService.getAllItems());
        model.addAttribute("disciplines", SportDiscipline.values());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Integer itemId,
                                @RequestParam(value = "discipline", required = false) String discipline,
                                Model model) {
        cartService.addItemToCart(itemId);
        if (selectedDiscipline != null) {
            return "redirect:/filter?discipline=" + selectedDiscipline;
        }
        model.addAttribute("items", cartService.getAllItems());
        model.addAttribute("disciplines", SportDiscipline.values());
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String getFilteredItems(@RequestParam("discipline") String discipline,  Model model) {
        selectedDiscipline = SportDiscipline.valueOf(discipline);
        model.addAttribute("items", itemRepository.findAllByDiscipline(selectedDiscipline));
        model.addAttribute("disciplines", SportDiscipline.values());
        return "home";
    }
}
