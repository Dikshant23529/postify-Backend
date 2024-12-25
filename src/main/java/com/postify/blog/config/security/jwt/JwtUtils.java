package com.postify.blog.config.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {

    private String jwtSecret = "92a692ad9290b163dba78fd6566f5dd6b6d850cafa0d9234408ab5336d4e687e6bdeb1047b47ac00a199cf0a5ab0ec63101fc7b9b187643772f96521231aab89ac09b92b23a2501c3bf68cf28ba12ba4c36e291372ad74485b384f4735774ab279e14971215404912621d30289ddc07934baef845ece65505705b242154469e265aa5deb743771eb9c047906ae2cc6a42a5a7dedab9e2534f42d6f302d15a0d4e6363b6942f050d1209704bcd40170037b3e0d9ad0dbd30294cae1afc661d98c7ca8cedaa8a0f83d53f18802cf1957767fdc7b59caf8eaa8607d7b10ca58c1bd4042b9ba810f649f17ddfe3d26d1c7101ce087938201df87758753dc3df4b386";

    int jwtExpirationMs = 10 * 60 * 1000;

    public String jwtTokenFromHeader(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            return jwtToken.substring(7);
        }

        return null;
    }

    public String generateJwtTokenFromUsername(String username) {
        return Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            System.out.println("Validate");
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
