package com.sendbox.kiosk.admin.menu.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuDto {

    private String name;
    private int price;
    private boolean status;
    private Integer salePercent;

    @Builder
    public MenuDto(String name, int price, boolean status, Integer salePercent) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.salePercent = salePercent;
    }
}
