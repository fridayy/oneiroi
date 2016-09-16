package ninja.harmless.security.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import ninja.harmless.security.JwtService
import ninja.harmless.security.model.JwtToken
import ninja.harmless.user.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
/**
 * @author bnjm@harmless.ninja - 9/14/16.
 */
@Service
class JwtServiceImpl implements JwtService {

    String secret

    @Autowired
    JwtServiceImpl(@Value('${secret}')String secret) {
        this.secret = secret
    }

    @Override
    JwtToken generateJWT(User user) {
        Objects.requireNonNull(secret)

        Map<String, Object> publicClaims = [:]
        publicClaims.putAll(user?.privileges?.rights)

        String compactJwt = Jwts.builder()
                .setSubject(user.basic.username)
                .setExpiration(new Date(System.currentTimeMillis() + 36000))
                .setClaims(publicClaims)
                .signWith(SignatureAlgorithm.HS256, secret.bytes.encodeBase64().toString())
                .compact()

        String[] jwtParts = compactJwt.split("\\.")

        return new JwtToken(header: jwtParts[0], payload: jwtParts[1], signature: jwtParts[2])
    }


    @Override
    boolean verifyJWT(JwtToken token) {
        try {
            Jwts.parser().setSigningKey(secret.bytes.encodeBase64().toString()).parseClaimsJws(token.toString())
        } catch(SignatureException | MalformedJwtException e) {
            println(e.message)
            return false
        }
        return true
    }

    @Override
    boolean verifyJWT(String token) {
        try {
            Jwts.parser().setSigningKey(secret.bytes.encodeBase64().toString()).parseClaimsJws(token)
        } catch (SignatureException | MalformedJwtException e) {
            println(e.message)
            return false
        }
        return true
    }
}
