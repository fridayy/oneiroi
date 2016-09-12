package ninja.harmless.user.controller

import ninja.harmless.user.UserService
import ninja.harmless.user.model.User
import ninja.harmless.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * @author bnjm@harmless.ninja - 9/12/16.
 */
@RestController
@RequestMapping(path = "/api/v1/")
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
    ResponseEntity<User> loginUser(@RequestBody User user) {
        if(!userService.auth(user)) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST)
        }
        return new ResponseEntity<User>(user, HttpStatus.CREATED)
    }

}
