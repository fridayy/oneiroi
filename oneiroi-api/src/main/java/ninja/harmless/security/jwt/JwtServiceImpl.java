package ninja.harmless.security.jwt;


import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author bnjm@harmless.ninja - 9/17/16.
 */
@Component
public class JwtServiceImpl<T> implements JwtService<T> {

    @Override
    public JsonWebToken generateJWT(T user, Map<String, Object> publicClaims, String secret) {
        Objects.requireNonNull(secret);
        String compactJwt = Jwts.builder()
                .setClaims(publicClaims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .setSubject(user.toString())
                .signWith(SignatureAlgorithm.HS256, Base64.encodeBase64URLSafeString(secret.getBytes()))
                .compact();
        String[] jwtParts = compactJwt.split("\\.");

        return new JsonWebToken(jwtParts[0], jwtParts[1], jwtParts[2]);
    }

    @Override
    public boolean verifyJWT(JsonWebToken token, String secret) {
        try {

            Jwts.parser().setSigningKey(Base64.encodeBase64URLSafeString(secret.getBytes())).parseClaimsJws(token.toString());
        } catch(SignatureException | MalformedJwtException | ExpiredJwtException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean verifyJWT(String token, String secret) {
        try {
            Jwts.parser().setSigningKey(Base64.encodeBase64URLSafeString(secret.getBytes())).parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
            return false;
        }
        return true;
    }

    @Override
    public String getSubject(String token, String secret) {
        String subject = "";
        try {
            subject = Jwts.parser().setSigningKey(Base64.encodeBase64URLSafeString(secret.getBytes())).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
        }
        return subject;
    }

}
