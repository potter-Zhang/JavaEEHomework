package whu.edu.ProductManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whu.edu.ProductManagement.domain.User;
import whu.edu.ProductManagement.security.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getId());
        if (passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(token);
        }
        else {
            return ResponseEntity.badRequest().body("invalid user");
        }

    }
}
