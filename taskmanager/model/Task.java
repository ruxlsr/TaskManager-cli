
package taskmanager.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Rux-Lsr
 */

// TODO("fix bug on date when update maybe when the task is newl")
public class Task {
    String id;
    String description;
    Status status;
    String updatedAt;
    String createdAt;

    
    public Task(String id, String description){
        this.id = id;
        this.status = Status.TODO;
        this.description = description;
         this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss"));
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss"));
    }

    public Task(String id, String description, Status status, String updatedAt,String createdAt){
        this.id = id;
        this.status = status;
        this.description = description;
        this.createdAt =createdAt;
        this.updatedAt = updatedAt;
    }
    public String toJson() {

        return "{\"id\":\"" + id +
                "\", \"description\":\"" + description + "\", " +
                "\"status\":\"" + status.toString() + "\", " +
                "\"createdAt\":\"" + createdAt + "\", " +
                "\"updatedAt\":\"" + updatedAt + "\"}";
    }
    @Override
    public String toString(){
        return id+" | "+description+" | "+status.toString()+" | "+ createdAt+ " | "+updatedAt;
    }
    
    public void toggleToInProgress(){
        this.status  = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss"));
    }
    
    public void toggleToTodo(){
        this.status  = Status.TODO;
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss"));
    }
    
    public void toggleToDone(){
        this.status  = Status.DONE;
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss"));
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss"));
    }
}
