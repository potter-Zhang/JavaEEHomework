package demo.impl;

import demo.domain.Role;

import demo.exception.MyException;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        demo.domain.User user = null;
        try {
            user = userService.getUser(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " is not found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            for (String auth : role.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }
        }
        return  User.builder()
                .username(username)

                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(authorities.toArray(new GrantedAuthority[authorities.size()]))
                .build();
    }


}
