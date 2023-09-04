package com.koozka.simpleshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal price;
    private String imgUrl;
    private SportDiscipline discipline;

    public Item(String name, BigDecimal price, String imgUrl, SportDiscipline discipline) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.discipline = discipline;
    }
}
