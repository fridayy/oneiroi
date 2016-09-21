package ninja.harmless.management.controller

import ninja.harmless.aspect.jwt.VerifyJWT
import ninja.harmless.management.UserManagementService
import ninja.harmless.user.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author bnjm@harmless.ninja - 9/16/16.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/v1/")
class UserManagementRestController {

    UserManagementService userManagementService

    @Autowired
    UserManagementRestController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService
    }

    @RequestMapping(value = "/usermanagement/users")
    @VerifyJWT
    ResponseEntity<Collection<User>> getLoggedInUsers(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<Collection<User>>(userManagementService.getActiveUser(), HttpStatus.OK)
    }
}
