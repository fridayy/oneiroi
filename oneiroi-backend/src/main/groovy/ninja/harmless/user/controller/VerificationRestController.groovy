package ninja.harmless.user.controller

import ninja.harmless.security.jwt.JwtService
import ninja.harmless.user.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author bnjm@harmless.ninja - 9/15/16.
 */
@RestController
@RequestMapping(path = "/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
class VerificationRestController {

    JwtService<User> jwtService

    @Autowired
    VerificationRestController(JwtService jwtService) {
        this.jwtService = jwtService
    }

    @RequestMapping(path = "/verify/{token:.+}", method = RequestMethod.GET)
    ResponseEntity<?> verifyTokenInPath(@PathVariable String token) {
        if(jwtService.verifyJWT(token, "abc")) {
            return new ResponseEntity<Object>(HttpStatus.OK)
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST)
    }
}
