
package taskmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rux-Lsr
 */
public class TaskList {
    List<Task> tasks;
    public TaskList(){
        tasks = new ArrayList<>();
    }
    public void addTask(String taskDescription) {
        tasks.add(new Task(String.valueOf(tasks.size()), taskDescription, Status.TODO, LocalDateTime.now().toString()));
        System.out.printf("Task added successfully (ID: %d)", tasks.size() - 1);
    }
    public void addTask(Task task){
        tasks.add(task);
        System.out.printf("Task added successfully (ID: %d)", task.id);
    }
    
    public void deleteTask(String id){
        tasks.remove(Integer.parseInt(id));
        System.out.printf("Task deleted successfully (ID: %s)", id);
    }
    public void update(String id, String updatedDescription){
        int intId = Integer.parseInt(id);
        String[] result = {tasks.get(intId).description, updatedDescription}; 
        tasks.get(intId).description = updatedDescription;
        
        System.out.printf("Task updated successfully (ID: %d) : (old)%s => (new)%s", intId, result[0], result[1]);
    }
    
    public void markTaskAsInProgress(String id){
        int intId = Integer.parseInt(id);
        tasks.get(intId).toggleToInProgress();
    }
    public void markTaskAsTodo(String id){
        int intId = Integer.parseInt(id);
        tasks.get(intId).toggleToTodo();
    }
    public void markTaskAsDone(String id){
        int intId = Integer.parseInt(id);
        tasks.get(intId).toggleToDone();
    }
    public StringBuilder toJson() {
        StringBuilder jsonBuild = new StringBuilder();
        jsonBuild.append("[");
        for (int i = 0; i < tasks.size(); i++) {
            jsonBuild.append(tasks.get(i).toJson());
            if (i < tasks.size() - 1) { // Ajouter une virgule si ce n'est pas le dernier élément
                jsonBuild.append(",");
            }
        }
        jsonBuild.append("]");

        System.out.println(jsonBuild);
        return jsonBuild;
    }
}
