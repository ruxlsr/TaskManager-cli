
package taskmanager;

import java.time.LocalDateTime;

/**
 *
 * @author Rux-Lsr
 */
public class Task {
    String id;
    String description;
    Status status;
    String updatedAt;
    
    public Task(String id, String description, Status status, String UpdatedAt){
        this.id = id;
        this.status = status;
        this.description = description;
        this.updatedAt = UpdatedAt;
    }
    public Task(String id, String description, Status status){
         this.id = id;
        this.status = status;
        this.description = description;
        this.updatedAt = LocalDateTime.now().toString();
    }
    public String toJson() {
        String json = "{\"id\":\"" + id +
                "\",\"description\":\"" + description + "\"," +
                "\"status\":\"" + status.toString() + "\"," +
                "\"updataedAt\":\"" + updatedAt + "\"}"; // Supprimer la virgule finale

        return json;
    }
    
    public void toggleToInProgress(){
        this.status  = Status.IN_PROGRESS;
    }
    
    public void toggleToTodo(){
        this.status  = Status.TODO;
    }
    
    public void toggleToDone(){
        this.status  = Status.DONE;
    }
}
