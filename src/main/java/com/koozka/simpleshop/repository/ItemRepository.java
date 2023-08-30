package com.koozka.simpleshop.repository;

import com.koozka.simpleshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
