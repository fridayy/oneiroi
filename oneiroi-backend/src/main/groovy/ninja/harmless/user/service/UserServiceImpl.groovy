package ninja.harmless.user.service

import ninja.harmless.management.UserManagementService
import ninja.harmless.security.jwt.JsonWebToken
import ninja.harmless.security.jwt.JwtService
import ninja.harmless.user.UserService
import ninja.harmless.user.exception.InvalidCredentialsException
import ninja.harmless.user.model.User
import ninja.harmless.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDateTime

/**
 * @author bnjm@harmless.ninja - 9/12/16.
 */
@Service
class UserServiceImpl implements UserService {

    UserRepository userRepository
    UserManagementService userManagementService
    JwtService<User> jwtService

    @Autowired
    UserServiceImpl(UserRepository userRepository, JwtService<User> jwtService, UserManagementService userManagementService) {
        this.userManagementService = userManagementService
        this.userRepository = userRepository
        this.jwtService = jwtService
    }

    @Override
    JsonWebToken auth(User user) {
        User u = userRepository.findByUsernameIgnoreCase(user.basic.username)
        if (u.basic.password.equals(user.basic.password)) {
            u.basic.lastLogin = LocalDateTime.now()
            userRepository.save(u)
            userManagementService.add(u)
            return jwtService.generateJWT(u, u.privileges.rights, "abc")
        }
        throw new InvalidCredentialsException("Wrong username / password")
    }

    @Override
    void logout(User user) {
        userManagementService.remove(user);
    }

    @Override
    void logout(String token) {
        userManagementService.removeByUsername(jwtService.getSubject(token))
    }
}
