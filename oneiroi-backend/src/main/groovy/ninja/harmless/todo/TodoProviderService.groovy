package ninja.harmless.todo

import ninja.harmless.todo.model.Todo
import ninja.harmless.todo.model.TodoStats

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
interface TodoProviderService {

    Collection<Todo> provideAll()

    TodoStats provideStatistics()
}