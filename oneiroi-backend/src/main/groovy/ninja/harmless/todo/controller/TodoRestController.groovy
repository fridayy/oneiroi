package ninja.harmless.todo.controller

import groovy.transform.TypeChecked
import ninja.harmless.todo.TodoProviderService
import ninja.harmless.todo.model.Todo
import ninja.harmless.todo.model.TodoStats
import ninja.harmless.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
@RestController
@RequestMapping(value = "api/v1/")
@TypeChecked
class TodoRestController {

    TodoProviderService todoProviderService
    TodoRepository repository

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
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/todos", method = RequestMethod.GET)
    Collection<Todo> todoEntries() {
        return todoProviderService.provideAll()
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/todo", method = RequestMethod.POST)
    ResponseEntity<Todo> createEntry(@RequestBody Todo entry) {
        repository.save(entry)
        return new ResponseEntity<Todo>(entry, HttpStatus.CREATED)
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/todo/{id}", method = RequestMethod.GET)
    Todo findOne(@PathVariable String id) {
        return repository.findOne(id)
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/todo", method = RequestMethod.PUT)
    ResponseEntity<Todo> updateEntry(@RequestBody Todo entry) {
        repository.save(entry)
        return new ResponseEntity<Todo>(entry, HttpStatus.OK)
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/todo/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Todo> deleteEntry(@PathVariable String id) {
        repository.delete(id)
        return new ResponseEntity<Todo>(HttpStatus.OK)
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(path = "/todos/stats", method = RequestMethod.GET)
    TodoStats getStatistics() {
        return todoProviderService.provideStatistics()
    }
}
