package ninja.harmless.user.controller

import ninja.harmless.security.model.JwtToken
import ninja.harmless.user.UserService
import ninja.harmless.user.model.BasicUserInformation
import ninja.harmless.user.model.User
import ninja.harmless.user.model.UserRights
import ninja.harmless.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * Provides REST endpoints for the user
 * @author bnjm@harmless.ninja - 9/12/16.
 */
@RestController
@RequestMapping(path = "/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
class UserRestController {

    UserRepository userRepository
    UserService userService

    @Autowired
    UserRestController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository
        this.userService = userService
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    Collection<User> getUsers() {
        return userRepository.findAll()
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    User getUserById(@PathVariable String id) {
        return userRepository.findOne(id)
    }

    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsernameIgnoreCase(username)
    }

    @RequestMapping(path = "/user/", method = RequestMethod.POST)
    ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user)
        return new ResponseEntity<User>(user, HttpStatus.CREATED)
    }


    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    ResponseEntity<JwtToken> loginUser(@RequestBody User user) {
        JwtToken token = userService.auth(user)
        return new ResponseEntity<JwtToken>(token, HttpStatus.ACCEPTED)
    }

    @RequestMapping(path = "/user/create", method = RequestMethod.GET)
    ResponseEntity<User> createMock() {
        User u = new User(basic:
                            new BasicUserInformation(username: "bnjm", password: "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"),
                         privileges:
                            new UserRights(rights: ["admin" : true])
        )
        userRepository.save(u)
        return new ResponseEntity<User>(u, HttpStatus.ACCEPTED)
    }

}
