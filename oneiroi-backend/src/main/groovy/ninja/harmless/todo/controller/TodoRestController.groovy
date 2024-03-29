package ninja.harmless.todo.controller

import ninja.harmless.aspect.jwt.VerifyJWT
import ninja.harmless.todo.TodoProviderService
import ninja.harmless.todo.model.Todo
import ninja.harmless.todo.model.TodoStats
import ninja.harmless.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.time.LocalDateTime

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
@RestController
@RequestMapping(value = "api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
class TodoRestController {

    TodoProviderService todoProviderService
    TodoRepository repository
    @Value('${secret}')
    String secret

    @Autowired
    TodoRestController(TodoProviderService todoProviderService, TodoRepository repository) {
        this.todoProviderService = todoProviderService
        this.repository = repository
    }

    /**
     * Returns all todo resources.
     * Cross Origin enabled to ensure the front-end can send ajax requests.
     * @return
     */
    @RequestMapping(path = "/todos", method = RequestMethod.GET)
    @VerifyJWT
    ResponseEntity<Collection<Todo>> todoEntries(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<Collection<Todo>>(todoProviderService.provideAll(), HttpStatus.OK)
    }

    @RequestMapping(path = "/todo", method = RequestMethod.POST)
    @VerifyJWT
    ResponseEntity<Todo> createEntry(@RequestHeader("Authorization") String token, @RequestBody Todo entry) {
        entry.created = LocalDateTime.now()
        repository.save(entry)
        return new ResponseEntity<Todo>(entry, HttpStatus.CREATED)
    }

    @RequestMapping(path = "/todo", method = RequestMethod.GET)
    @VerifyJWT
    ResponseEntity<Todo> findOne(@RequestHeader("Authorization") String token, @RequestParam(value = "id", required = true) String id) {
        return new ResponseEntity<Todo>(repository.findOne(id), HttpStatus.OK)
    }

    @RequestMapping(path = "/todo", method = RequestMethod.PUT)
    @VerifyJWT
    ResponseEntity<Todo> updateEntry(@RequestHeader("Authorization") String token, @RequestBody Todo entry) {
        repository.save(entry)
        return new ResponseEntity<Todo>(entry, HttpStatus.OK)
    }

    @RequestMapping(path = "/todo/{id}", method = RequestMethod.DELETE)
    @VerifyJWT
    ResponseEntity<Todo> deleteEntry(@RequestHeader("Authorization") String token, @PathVariable String id) {
        repository.delete(id)
        return new ResponseEntity<Todo>(HttpStatus.OK)
    }

    @RequestMapping(path = "/todos/stats", method = RequestMethod.GET)
    @VerifyJWT
    ResponseEntity<TodoStats> getStatistics(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<TodoStats>(todoProviderService.provideStatistics(), HttpStatus.OK)
    }

    @RequestMapping(path = "/todos/test")
    @VerifyJWT
    String test(@RequestHeader("Authorization") String header) {
        return header
    }
}
