package whu.edu.ProductManagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import whu.edu.ProductManagement.domain.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbUserDetailService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        whu.edu.ProductManagement.domain.User user = userDao.selectById(s);
        if (user == null)
            throw new UsernameNotFoundException("user name " + s + " not found");

        return User.builder()
                .username(user.getId())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(getAuth(userDao, user))
                .build();
    }

    private static GrantedAuthority[] getAuth(UserDao userDao, whu.edu.ProductManagement.domain.User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : userDao.getRoles(user.getId())) {
            String[] auths = role.getAuthorities().split("[.]");
            for (String auth : auths) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }
        }
        return authorities.toArray(new GrantedAuthority[authorities.size()]);
    }
}
