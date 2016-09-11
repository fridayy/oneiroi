package ninja.harmless.todo.model

import org.springframework.data.annotation.Id

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
class Todo {
    @Id
    String id

    String title
    String description
    Date created
    Date dueDate
    boolean isDone
}
