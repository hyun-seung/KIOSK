package com.sendbox.kiosk.service;

import com.sendbox.kiosk.menu.domain.Menu;
import com.sendbox.kiosk.menu.domain.MenuDto;
import com.sendbox.kiosk.menu.repository.MenuRepository;
import com.sendbox.kiosk.menu.service.MenuService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuService menuService;


    @AfterEach
    void tearDown() {
        menuRepository.deleteAllInBatch();
    }

    @DisplayName("메뉴를 하나 추가한다.")
    @Test
    public void addMenuTest() {
        //given
        String menuName = "Americano";
        MenuDto menu1 = MenuDto.builder()
                .name(menuName)
                .price(1500)
                .status(true)
                .salePercent(null)
                .build();

        //when
        Menu menu = menuService.addMenu(menu1);

        //then
        assertEquals(menuName, menu.getName());
        assertEquals(1500, menu.getPrice());
        assertEquals(true, menu.isStatus());
        assertEquals(null, menu.getSalePercent());
    }

}