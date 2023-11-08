package com.sendbox.kiosk.service;

import com.sendbox.kiosk.domain.Menu;
import com.sendbox.kiosk.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu addMenu(Menu menu1) {
        return null;
    }

    public long deleteMenu() {
        return 0;
    }
}
