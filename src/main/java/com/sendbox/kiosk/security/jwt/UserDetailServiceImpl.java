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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userTell) throws UsernameNotFoundException {
        List<User> users = userRepository.findByTell(userTell);

        User user = users.get(0);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Iterator<Role> iterator = user.getRoles().iterator();
        while (iterator.hasNext()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(iterator.next().getName()));
        }

        return new org.springframework.security.core.userdetails
                .User(user.getTell(), user.getPassword(), grantedAuthorities);
    }
}
