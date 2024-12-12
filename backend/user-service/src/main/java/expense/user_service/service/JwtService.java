package expense.user_service.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    private final String SECRET = "vIflef3puAxyyp9Z5hI09zH5EY5C9rcykPsuz3Ev/6D1z0AbuocMrm0K3/CXwJFV\r\n";


    public String  generateToken(String userName) {
        Map<String , Object> claims = new HashMap<>();
        return createToken(claims , userName);
    }

    public String createToken(Map<String , Object> claims , String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
    }


    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void validateToken(final String token) {
        // TODO Auto-generated method stub
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);

    }
}
