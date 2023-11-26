package com.sendbox.kiosk.redis.repository;

import com.sendbox.kiosk.redis.domain.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<UserToken, String> {
}
