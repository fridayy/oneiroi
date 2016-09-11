package ninja.harmless.todo.service

import ninja.harmless.todo.TodoProviderService
import ninja.harmless.todo.model.Todo
import ninja.harmless.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
@Service
class TodoProviderServiceImpl implements TodoProviderService {

    TodoRepository repository

    @Autowired
    TodoProviderServiceImpl(TodoRepository repository) {
        this.repository = repository
    }

    @Override
    Collection<Todo> provideAll() {
        return repository.findAll()
    }
}
