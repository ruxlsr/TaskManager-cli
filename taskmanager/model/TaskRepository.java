
package taskmanager.model;

import taskmanager.MessageDisplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepository {
    List<Task> tasks;
    private static final AtomicLong idGenerator = new AtomicLong(0);
    public TaskRepository(){
        tasks = new ArrayList<>();
    }

    public void addTask(String taskDescription) {
        Task newTask  = new Task(String.valueOf(idGenerator.incrementAndGet()), taskDescription);
        tasks.add(newTask);
    }

    public void addTask(Task task){
        task.id = String.valueOf(idGenerator.incrementAndGet());
        tasks.add(task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }

    public void updateDescription(String id, String updatedDescription){
        for (Task task: tasks){
            if(task.id.equals(id)){
                tasks.get(Integer.parseInt(id)-1).setDescription(updatedDescription);
                //MessageDisplayer.debMessage("task updated: "+task.description+" => "+updatedDescription);
                return;
            }
        }
        MessageDisplayer.errMessage("The id you passed doen't exists ID:"+id);
    }
    
    public void markTaskAsInProgress(String id){
        for (Task task: tasks){
            if(task.id.equals(id)){
                tasks.get(Integer.parseInt(id)-1).toggleToInProgress();
               // MessageDisplayer.debMessage("Marked as in progress");
                return;
            }
        }
        MessageDisplayer.errMessage("The id you passed doen't exists ID:"+id);
    }

    public void markTaskAsDone(String id){
        for (Task task: tasks){
            if(task.id.equals(id)){
                tasks.get(Integer.parseInt(id)-1).toggleToDone();
                //MessageDisplayer.debMessage("Marked as done");
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
        return jsonBuild.toString();
    }

    public void list(){
        tasks.forEach(task -> {
            System.out.printf("%-4s %-25s %-15s %-25s %-25s\n", task.id, task.description, task.status.toString(), task.createdAt, task.updatedAt);

        });
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
