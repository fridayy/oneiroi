package ninja.harmless.todo

import ninja.harmless.todo.model.Todo

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
interface TodoProviderService {

    Collection<Todo> provideAll()
}