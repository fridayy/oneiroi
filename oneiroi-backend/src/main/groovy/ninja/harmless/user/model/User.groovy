package ninja.harmless.user.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
/**
 * @author bnjm@harmless.ninja - 9/12/16.
 */
@Document(collection = "user")
class User {
    @Id
    String id
    BasicUserInformation basic
    UserRights privileges
}
