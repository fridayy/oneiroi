package ninja.harmless.management.service

import ninja.harmless.management.UserManagementService
import ninja.harmless.user.model.User
import ninja.harmless.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.concurrent.ConcurrentHashMap

/**
 * @author bnjm@harmless.ninja - 9/15/16.
 */
@Service
class UserManagementServiceImpl implements UserManagementService {

    UserRepository userRepository

    @Autowired
    UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }
    private Set<User> activeUsers = Collections.newSetFromMap(new ConcurrentHashMap<>())

    @Override
    void add(User user) {
        Objects.requireNonNull(user)
        activeUsers.add(user)
    }

    @Override
    void removeByUsername(String username) {
        activeUsers.each {
            if(it.basic.username.equalsIgnoreCase(username)) {
                remove(it)
            }
        }
    }

    @Override
    void remove(User user) {
        Objects.requireNonNull(user)
        activeUsers.remove(user)
    }

    @Override
    Set<User> getActiveUser() {
        return activeUsers
    }

    @Override
    void grantPrivilege(User user, String privilege) {
        user.privileges.rights.put(privilege, true)
        userRepository.save(user)
    }

    @Override
    void revokePrivilege(User user, String privilege) {
        user.privileges.rights.put(privilege, false)
        userRepository.save(user)
    }
}
