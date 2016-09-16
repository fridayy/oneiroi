package ninja.harmless.security

import ninja.harmless.security.model.JwtToken
import ninja.harmless.user.model.User

/**
 * @author bnjm@harmless.ninja - 9/14/16.
 */
interface JwtService {
    JwtToken generateJWT(User user)
    boolean verifyJWT(JwtToken token)
    boolean verifyJWT(String token)
}