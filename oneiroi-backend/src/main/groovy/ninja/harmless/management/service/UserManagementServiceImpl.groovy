package ninja.harmless.management.service

import ninja.harmless.management.UserManagementService
import ninja.harmless.user.model.User
import org.springframework.stereotype.Service
/**
 * @author bnjm@harmless.ninja - 9/15/16.
 */
@Service
class UserManagementServiceImpl implements UserManagementService {

    private List<User> activeUsers = new ArrayList<>()

    @Override
    void add(User user) {
        Objects.requireNonNull(user)
        activeUsers.add(user)
    }

    @Override
    Set<User> getActiveUser() {
        return activeUsers
    }

    @Override
    void grantPrivilege(User user, String privilege) {
        user.privileges.rights.put(privilege, true)
    }

    @Override
    void revokePrivilege(User user, String privilege) {
        user.privileges.rights.put(privilege, false)
    }
}
