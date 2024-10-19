
package taskmanager.model;

import taskmanager.MessageDisplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author Rux-Lsr
 */
public class TaskRepository {
    List<Task> tasks;
    private static final AtomicLong idGenerator = new AtomicLong(0);
    public TaskRepository(){
        tasks = new ArrayList<>();
    }

    public void addTask(String taskDescription) {
        Task newTask  = new Task(String.valueOf(idGenerator.incrementAndGet()), taskDescription);
        //System.out.println(newTask.toString());
        tasks.add(newTask);
        //MessageDisplayer.debMessage("Task added successfully. ID:"+ newTask.id);
    }

    public void addTask(Task task){
        task.id = String.valueOf(idGenerator.incrementAndGet());
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
        //MessageDisplayer.debMessage(" Task deleted successfully ID: "+task.id);
    }

    public void updateDescription(String id, String updatedDescription){
        int intId = Integer.parseInt(id);
        String[] result = {tasks.get(intId).description, updatedDescription}; 
        tasks.get(intId).setDescription(updatedDescription) ;
        
        System.out.printf("Task updated successfully (ID: %d) : (old)%s => (new)%s", intId, result[0], result[1]);
    }
    
    public void markTaskAsInProgress(String id){
        for (Task task: tasks){
            if(task.id.equals(id)){
                tasks.get(Integer.parseInt(id)-1).toggleToInProgress();
                MessageDisplayer.debMessage("Marked as in progress");
                return;
            }
        }
        MessageDisplayer.errMessage("The id you passed doen't exists ID:"+id);
    }
    public void markTaskAsTodo(String id){
        int intId = Integer.parseInt(id);
        tasks.get(intId).toggleToTodo();
    }
    public void markTaskAsDone(String id){
        for (Task task: tasks){
            if(task.id.equals(id)){
                tasks.get(Integer.parseInt(id)-1).toggleToDone();
                MessageDisplayer.debMessage("Marked as done");
                return;
            }
        }
        MessageDisplayer.errMessage("The id you passed doen't exists ID:"+id);
    }
    public String toJson() {
        StringBuilder jsonBuild = new StringBuilder();
        jsonBuild.append("[\n");
        for (int i = 0; i < tasks.size(); i++) {
            jsonBuild.append(tasks.get(i).toJson());
            if (i < tasks.size() - 1) {
                jsonBuild.append(",\n");
            }
        }
        jsonBuild.append("\n]");

        //System.out.println(jsonBuild);
        return jsonBuild.toString();
    }

    public void list(){
        tasks.forEach(task -> System.out.println(task.toString()));
    }

    public void listTodo(){
        tasks.stream().filter(task -> task.status == Status.TODO).forEach(System.out::println);
    }
    public void listDone(){
        tasks.stream().filter(task -> task.status == Status.DONE).forEach(System.out::println);
    }
    public void listProgress(){
        tasks.stream().filter(task -> task.status == Status.IN_PROGRESS).forEach(System.out::println);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
