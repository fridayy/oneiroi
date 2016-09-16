package ninja.harmless.todo.service

import ninja.harmless.todo.TodoProviderService
import ninja.harmless.todo.model.Todo
import ninja.harmless.todo.model.TodoStats
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
    TodoStats provideStatistics() {
        int totalCount = provideAll().size()
        int markedDone = repository.countMarkedDone()
        int open = totalCount - markedDone
        double percentageDone = 0d
        if(totalCount > 0) {
            percentageDone = (markedDone / totalCount) * 100
        }
        TodoStats stats = new TodoStats(totalTodos: totalCount,
                markedDone: markedDone,
                open: open,
                percentageDone: percentageDone)
        return stats
    }

    @Override
    Collection<Todo> provideAll() {
        return repository.findAll()
    }
}
