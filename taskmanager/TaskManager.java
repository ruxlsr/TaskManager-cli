
package taskmanager;

import taskmanager.model.Status;
import taskmanager.model.Task;
import taskmanager.model.TaskRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class TaskManager {
    final Path JSON_FILE_PATH = Path.of("./task.json");
    TaskRepository tasksList;

    public TaskManager(){
        tasksList = new TaskRepository();
        try{
            loadTaskInMemory();
        }catch (Exception exception){
            MessageDisplayer.errMessage(exception);
        }
    }

    public void loadTaskInMemory() throws Exception {
        if(!Files.exists(JSON_FILE_PATH)){
            Files.createFile(JSON_FILE_PATH);
            Files.writeString(JSON_FILE_PATH, "[\n]");
        }

        String json = Files.readString(JSON_FILE_PATH);
        parseJsonString(json);
    }

    public void parseJsonString(String json) throws Exception {
        json = json.replace("[", "").replace("]", "");
        json = json.trim();
        String[] jsonObjects = json.split("},");

        int i = 0;
        for(String jsonObject: jsonObjects){
            jsonObject = jsonObject.replace("\n", "").trim();
            jsonObject = jsonObject.endsWith("\"") ? jsonObject+"}":jsonObject;
            jsonObjects[i++] = jsonObject;
        }

        for(String jsonObject: jsonObjects){
            String[] tasksString = getParsedObjectAttributes(jsonObject);
            tasksList.addTask(
                new Task(
                    tasksString[0].split(":")[1].replace("\"", " ").trim(),
                    tasksString[1].split(":")[1].replace("\"", " ").trim(),
                    Status.getStatus(tasksString[2].split(":")[1].replace("\"", " ").trim()) ,
                    tasksString[4].split(":")[1].replace("\"", " ").trim(),
                    tasksString[3].split(":")[1].replace("\"", " ").trim()
                )
            );
        }

    }

    public String[] getParsedObjectAttributes(String jsonObject){
        jsonObject = jsonObject.replace("{", " ").replace("}", " ").trim();

        return jsonObject.split(", ");
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
