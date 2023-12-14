package com.sendbox.kiosk.redis.repository;

import com.sendbox.kiosk.login.domain.TokenDto;
import com.sendbox.kiosk.redis.domain.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class UserTokenRepository {

    @Autowired
    private final RedisTemplate<String, UserToken> redisTemplate;

    private final HashOperations<String, String, String> hashOperations;

    public UserTokenRepository(RedisTemplate<String, UserToken> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public TokenDto findByTell(String tell) {
        String refreshToken = "";

        if (hashOperations.hasKey(tell, refreshToken)) {
            refreshToken = hashOperations.get(tell, refreshToken);
        } else {
            // 예외 처리
            refreshToken = "못 찾았어용..";
        }

        String accessToken = hashOperations.get(tell, refreshToken);
        long expireInSeconds = redisTemplate.getExpire(tell);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expireIn(expireInSeconds)
                .build();
    }

    public TokenDto save(UserToken userToken, long ttlInSeconds) {
        redisTemplate.opsForHash().put(userToken.getUserTell(), userToken.getToken().getRefreshToken(), userToken.getToken().getAccessToken());

        boolean isSuccess = setTTL(userToken.getUserTell(), ttlInSeconds);
        if (!isSuccess) {
            ttlInSeconds = -1;
        }

        return TokenDto.builder()
                .accessToken(userToken.getToken().getAccessToken())
                .refreshToken(userToken.getToken().getRefreshToken())
                .expireIn(ttlInSeconds)
                .build();
    }

    private boolean setTTL(String tell, long ttlInSeconds) {
        return redisTemplate.expire(tell, ttlInSeconds, TimeUnit.SECONDS);
    }
}
