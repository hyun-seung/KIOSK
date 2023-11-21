package com.sendbox.kiosk.login.service;

import com.sendbox.kiosk.login.domain.LoginRequestDto;
import com.sendbox.kiosk.login.domain.Token;
import com.sendbox.kiosk.login.repository.TokenRedisRepository;
import com.sendbox.kiosk.security.jwt.JwtTokenProvider;
import com.sendbox.kiosk.login.domain.TokenDto;
import jakarta.transaction.Transactional;
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
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private TokenRedisRepository tokenRepository;


    public TokenDto login(LoginRequestDto userLoginReqDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginReqDto.getUserName(),
                            userLoginReqDto.getTell()
                    )
            );
        } catch (BadCredentialsException e) {
            log.error("로그인 실패 :: 전화번호가 달라용");
            return null;
        }

        TokenDto tokenDto = new TokenDto(
                jwtTokenProvider.createAccessToken(authentication),
                jwtTokenProvider.createRefreshToken(authentication)
        );

        Token token = Token.builder()
                .tokenDto(tokenDto)
                .build();

        tokenRepository.save(token);


        return tokenDto;
    }
}
