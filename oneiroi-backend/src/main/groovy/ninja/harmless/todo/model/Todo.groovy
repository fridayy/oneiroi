package ninja.harmless.todo.model

import org.springframework.data.annotation.Id

import java.time.LocalDateTime

/**
 * @author bnjm@harmless.ninja - 9/11/16.
 */
class Todo {
    @Id
    String id

    String title
    String description
    String created
    boolean isDone

    void setCreated(String created) {
        this.created = LocalDateTime.now().toString()
    }
}
