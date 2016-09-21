package ninja.harmless.security.jwt;

import java.util.Map;

/**
 * @author bnjm@harmless.ninja - 9/17/16.
 */
public interface JwtService<T> {
    JsonWebToken generateJWT(T user, Map<String, Object> publicClaims, String secret);
    boolean verifyJWT(JsonWebToken token, String secret);
    boolean verifyJWT(String token, String secret);
    String getSubject(String token, String secret);
}