package com.sendbox.kiosk.user.repository;

import com.sendbox.kiosk.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByPhoneNumber(String phoneNumber);
}
