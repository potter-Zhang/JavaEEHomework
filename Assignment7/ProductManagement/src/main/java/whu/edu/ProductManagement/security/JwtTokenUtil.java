package whu.edu.ProductManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    public static final Long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L;

    private String secret = "db9d654c4860d4d37406d1ffcd92be5756a9ea94c03464aba0e475dc88932fee2278a23f6e6953a24d089ad09d99ac9d07ebd223a7f264fa2a7bea8a779c7946";

    public Claims extractClaimFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        return validateClaim(extractClaimFromToken(token),userDetails);
    }


    public boolean validateClaim(Claims claim,UserDetails userDetails) {
        return userDetails != null &&
                claim.getSubject().equals(userDetails.getUsername())
                && !claim.getExpiration().before(new Date());
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        Claims claim = extractClaimFromToken(token);
        return claim.getExpiration().before(new Date());
    }


}

