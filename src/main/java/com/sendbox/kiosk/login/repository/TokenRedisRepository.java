package com.sendbox.kiosk.login.repository;

import com.sendbox.kiosk.login.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRedisRepository extends CrudRepository<Token, String> {
}
