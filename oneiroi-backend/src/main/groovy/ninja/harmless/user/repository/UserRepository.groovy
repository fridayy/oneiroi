package ninja.harmless.user.repository

import ninja.harmless.user.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

/**
 * @author bnjm@harmless.ninja - 9/12/16.
 */
interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'basic.username' : ?0}}")
    User findByUsernameIgnoreCase(String username)
}
