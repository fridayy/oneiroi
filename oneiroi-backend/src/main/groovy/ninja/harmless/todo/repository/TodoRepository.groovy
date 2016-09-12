package ninja.harmless.todo.repository

import ninja.harmless.todo.model.Todo
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
@Document(collection = "todo")
interface TodoRepository extends MongoRepository<Todo, String> {
    @Query(value = "{'isDone' : true}",count = true)
    int countMarkedDone()
}