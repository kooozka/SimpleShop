package com.koozka.simpleshop;

import com.koozka.simpleshop.model.Item;
import com.koozka.simpleshop.model.SportDiscipline;
import com.koozka.simpleshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {
    private final ItemRepository itemRepository;

    @Autowired
    public DbInit(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
                new Item("Tennis Racquet", new BigDecimal("499.99"), "https://media.istockphoto.com/id/491945650/pl/zdj%C4%99cie/zbli%C5%BCenie-z-rakieta-do-tenisa-ziemnego.jpg?s=612x612&w=0&k=20&c=6jL0jnOGg3JlyZK_EW1EV6nY-8axWUS6ovQufMBPedE=", SportDiscipline.TENNIS),
                new Item("Tennis Balls", new BigDecimal("39.99"), "https://dotenisa.pl/images/thumbs/360_440/340926_dunlop_01.jpg", SportDiscipline.TENNIS),
                new Item("T-shirt", new BigDecimal("99.99"), "https://domtenisa.pl/wp-content/uploads/270.jpg", SportDiscipline.BASKETBALL)

        ));
    }
}
