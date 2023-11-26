package com.sendbox.kiosk.member.user.repository;

import com.sendbox.kiosk.member.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByTell(String tell);
}
