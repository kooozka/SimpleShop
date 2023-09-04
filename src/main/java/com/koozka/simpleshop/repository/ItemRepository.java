package com.koozka.simpleshop.repository;

import com.koozka.simpleshop.model.Item;
import com.koozka.simpleshop.model.SportDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findAllByDiscipline(SportDiscipline sportDiscipline);
}
