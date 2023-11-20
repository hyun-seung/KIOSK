package com.sendbox.kiosk.member.user.repository;

import com.sendbox.kiosk.member.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByPhoneNumber(String phoneNumber);
}
