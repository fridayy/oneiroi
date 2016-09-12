package ninja.harmless.user

import ninja.harmless.user.model.User

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
interface UserService {
    boolean auth(User user)
}