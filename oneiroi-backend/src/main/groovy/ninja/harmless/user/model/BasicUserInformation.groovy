package ninja.harmless.user.model

import java.time.LocalDateTime

/**
 * @author bnjm@harmless.ninja - 9/14/16.
 */
class BasicUserInformation {
    String username
    String password
    LocalDateTime createdOn
    LocalDateTime lastLogin
}
