package com.sendbox.kiosk.security.jwt;

import com.sendbox.kiosk.common.Role;
import com.sendbox.kiosk.member.user.domain.User;
import com.sendbox.kiosk.member.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> {
                    log.error("잘못된 유저 번호입니다.");
                    return null;
                });

        log.info("loadByUsername");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(user.getPhoneNumber());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Iterator<Role> iterator = user.getRoles().iterator();
        while(iterator.hasNext()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(iterator.next().getName()));
        }

        return new org.springframework.security.core.userdetails
                .User(Long.toString(user.getId()), password, grantedAuthorities);
    }
}
