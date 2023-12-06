package com.sendbox.kiosk.redis.domain;

import com.sendbox.kiosk.login.domain.Token;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash(value="UserToken")
public class UserToken implements Serializable {

    @Id
    private String userTell;

    private Token token;

    @Builder
    public UserToken(String userTell, Token token) {
        this.userTell = userTell;
        this.token = token;
    }
}
