package com.sendbox.kiosk.admin.menu.repository;

import com.sendbox.kiosk.admin.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByName(String name);

    void deleteByName(String name);
}
