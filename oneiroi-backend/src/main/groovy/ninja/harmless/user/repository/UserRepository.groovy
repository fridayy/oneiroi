package ninja.harmless.user.repository

import ninja.harmless.user.model.User
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author bnjm@harmless.ninja - 9/12/16.
 */
interface UserRepository extends MongoRepository<User, String> {
    User findByUsernameIgnoreCase(String username)
}
