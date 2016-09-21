package ninja.harmless.management

import ninja.harmless.user.model.User

/**
 * @author bnjm@harmless.ninja - 9/15/16.
 */
interface UserManagementService {
    void add(User user)
    void removeByUsername(String username)
    void remove(User user)
    Set<User> getActiveUser()
    void grantPrivilege(User user, String privilege)
    void revokePrivilege(User user, String privilege)
}