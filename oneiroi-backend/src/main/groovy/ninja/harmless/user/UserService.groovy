package ninja.harmless.user

import ninja.harmless.security.jwt.JsonWebToken
import ninja.harmless.user.model.User
/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
interface UserService {
    /**
     * Tries to find the user within the database and issues a JSON web token if the login is correct.
     * @param user
     * @return
     */
    JsonWebToken auth(User user)
    void logout(User user)
    void logout(String token)
}