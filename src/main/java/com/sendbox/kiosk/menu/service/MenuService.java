package com.sendbox.kiosk.menu.service;

import com.sendbox.kiosk.menu.domain.Menu;
import com.sendbox.kiosk.menu.domain.MenuDto;
import com.sendbox.kiosk.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu addMenu(MenuDto menuDto) {
        Menu newMenu = new Menu(menuDto);
        return menuRepository.save(newMenu);
    }

    public long deleteMenu() {
        return 0;
    }
}
