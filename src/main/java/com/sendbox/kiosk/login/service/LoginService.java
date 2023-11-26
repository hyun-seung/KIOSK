package com.sendbox.kiosk.login.service;

import com.sendbox.kiosk.login.domain.LoginRequestDto;
import com.sendbox.kiosk.login.domain.Token;
import com.sendbox.kiosk.login.domain.TokenDto;
import com.sendbox.kiosk.redis.domain.UserToken;
import com.sendbox.kiosk.redis.repository.UserTokenRepository;
import com.sendbox.kiosk.security.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserTokenRepository tokenRepository;


    public TokenDto login(LoginRequestDto userLoginReqDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginReqDto.getTell(),
                            userLoginReqDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            log.error("로그인 실패");
            return null;
        }

        Token token = Token.builder()
                .accessToken(jwtTokenProvider.createAccessToken(authentication))
                .refreshToken(jwtTokenProvider.createRefreshToken(authentication))
                .build();

        UserToken userToken = UserToken.builder()
                .userTell(userLoginReqDto.getTell())
                .token(token)
                .build();

        UserToken saveUserToken = tokenRepository.save(userToken);

        return TokenDto.builder()
                .accessToken(saveUserToken.getToken().getAccessToken())
                .refreshToken(saveUserToken.getToken().getRefreshToken())
                .build();
    }
}
