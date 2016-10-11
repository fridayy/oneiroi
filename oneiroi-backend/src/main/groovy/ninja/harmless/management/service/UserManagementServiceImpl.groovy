package ninja.harmless.management.service

import groovy.transform.TypeChecked
import ninja.harmless.management.UserManagementService
import ninja.harmless.user.model.User
import ninja.harmless.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

/**
 * @author bnjm@harmless.ninja - 9/15/16.
 */
@Service
@TypeChecked
class UserManagementServiceImpl implements UserManagementService {

    UserRepository userRepository
    private Set<User> activeUsers = Collections.newSetFromMap(new ConcurrentHashMap<User, Boolean>())

    @Autowired
    UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    void add(User user) {
        Objects.requireNonNull(user)
        User u = activeUsers.find {
            user.basic.username == it.basic.username
        }
        if(u == null) {
            activeUsers.add(user)
        } else {
            user.basic.lastLogin = LocalDateTime.now()
        }
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
        return Collections.unmodifiableSet(activeUsers)
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
