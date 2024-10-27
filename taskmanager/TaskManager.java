
package taskmanager;

import taskmanager.model.Status;
import taskmanager.model.Task;
import taskmanager.model.TaskRepository;
import taskmanager.utils.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class TaskManager {
    final Path JSON_FILE_PATH = Path.of("./task.json");
    TaskRepository tasksList;

    public TaskManager(){
        tasksList = new TaskRepository();
        try{
            tasksList = JSONParser.loadTaskInMemory(JSON_FILE_PATH);
        }catch (Exception exception){
            MessageDisplayer.errMessage(exception);
        }
    }

    public void add(String description){
        tasksList.addTask(description);
        saveTask();
    }

    public void delete(String id) throws Exception {
        for(Task task: tasksList.getTasks()){
            if(task.getId().equals(id)) {
                tasksList.deleteTask(task);
                saveTask();
                return;
            }
        }

        throw new Exception("The id("+id+") you passed doesn't exists");
    }

    public void saveTask()  {
        try {
            Files.writeString(JSON_FILE_PATH, tasksList.toJson());
        } catch (IOException e) {
            MessageDisplayer.errMessage(e);
        }
    }

    public  void listTask(){
        tasksList.list();
    }
    public  void listTaskTodo(){
        tasksList.listTodo();
    }
    public  void listTaskInProgress(){
        tasksList.listProgress();
    }
    public  void listTaskDone(){
        tasksList.listDone();
    }

    public void markAsInProgress(String id){
        tasksList.markTaskAsInProgress(id);
        saveTask();
    }
    public void markAsDone(String id){
        tasksList.markTaskAsDone(id);
        saveTask();
    }

    public void updateDescription(String id, String newDescription){
        tasksList.updateDescription(id, newDescription);
        saveTask();
    }
} 
