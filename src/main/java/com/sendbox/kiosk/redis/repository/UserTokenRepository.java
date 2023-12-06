package com.sendbox.kiosk.redis.repository;

import com.sendbox.kiosk.login.domain.TokenDto;
import com.sendbox.kiosk.redis.domain.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserTokenRepository {

    @Autowired
    private RedisTemplate<String, UserToken> redisTemplate;

    private static final String USER_TOKEN_KEY = "UserToken";

    public TokenDto save(UserToken userToken, long ttlInSeconds) {
        redisTemplate.delete(USER_TOKEN_KEY);

        redisTemplate.opsForHash().put(USER_TOKEN_KEY, userToken.getUserTell(), userToken);

        boolean isSuccess = setTTL(userToken.getUserTell(), ttlInSeconds);

        if (isSuccess == false) {
            ttlInSeconds = -1;
        }

        return TokenDto.builder()
                .accessToken(userToken.getToken().getAccessToken())
                .refreshToken(userToken.getToken().getRefreshToken())
                .expireIn(ttlInSeconds)
                .build();
    }

    private boolean setTTL(String tell, long ttlInSeconds) {
        String key = createKey(tell);
        return redisTemplate.expire(USER_TOKEN_KEY, ttlInSeconds, TimeUnit.SECONDS);
    }

    private String createKey(String tell) {
        StringBuilder sb = new StringBuilder();
        sb.append(USER_TOKEN_KEY).append(":").append(tell);
        return sb.toString();
    }
}
