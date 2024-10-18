
package taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Rux-Lsr
 */
public class TaskList {
    List<Task> tasks;
    private static final AtomicLong idGenerator = new AtomicLong(0);
    public TaskList(){
        tasks = new ArrayList<>();
    }

    public void addTask(String taskDescription) {
        Task newTask  = new Task(String.valueOf(idGenerator.incrementAndGet()), taskDescription);
        System.out.println(newTask.toString());
        tasks.add(newTask);
        System.out.printf("Task added successfully (ID: %d)", tasks.size() - 1);

    }
    public void addTask(Task task){
        //System.out.println(task.toString());
        task.id = String.valueOf(idGenerator.incrementAndGet());
        tasks.add(task);

        System.out.printf("Task loaded successfully (ID: %s)\n", task.id);
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
        jsonBuild.append("[\n");
        for (int i = 0; i < tasks.size(); i++) {
            jsonBuild.append(tasks.get(i).toJson());
            if (i < tasks.size() - 1) {
                jsonBuild.append(",\n");
            }
        }
        jsonBuild.append("\n]");

        System.out.println(jsonBuild);
        return jsonBuild;
    }

    public void list(){
        tasks.forEach(task -> System.out.println(task.toString()));
    }

    public void listTodo(){
        tasks.stream().filter(task -> task.status == Status.TODO).forEach(task -> System.out.println(task.toString()));
    }
    public void listDone(){
        tasks.stream().filter(task -> task.status == Status.DONE).forEach(task -> System.out.println(task.toString()));
    }
    public void listProgress(){
        tasks.stream().filter(task -> task.status == Status.IN_PROGRESS).forEach(task -> System.out.println(task.toString()));
    }

}
