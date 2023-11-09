package com.sendbox.kiosk.admin.menu.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "BEVERAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean status = true;

    @Column(name = "SALE_PERCENT")
    private Integer salePercent;

    @Builder
    public Menu(String name, int price, boolean status, Integer salePercent) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.salePercent = salePercent;
    }

    public Menu(MenuDto menuDto) {
        this.name = menuDto.getName();
        this.price = menuDto.getPrice();
        this.status = menuDto.isStatus();
        this.salePercent = menuDto.getSalePercent();
    }
}
