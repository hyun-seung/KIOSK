package com.sendbox.kiosk.admin.menu.service;

import com.sendbox.kiosk.admin.menu.repository.MenuRepository;
import com.sendbox.kiosk.admin.menu.domain.Menu;
import com.sendbox.kiosk.admin.menu.domain.MenuDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu addMenu(MenuDto menuDto) {
        Menu newMenu = new Menu(menuDto);
        return menuRepository.save(newMenu);
    }

    public void deleteMenuByName(String name) {
        menuRepository.deleteByName(name);
    }
}
