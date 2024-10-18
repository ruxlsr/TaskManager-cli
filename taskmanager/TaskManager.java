
package taskmanager;

/*
 * Ajouter des taches,
 * Recuperer des taches
 * supprimer des taches
 * modifier des taches
 * */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


public class TaskManager {
    final Path JSON_FILE_PATH = Path.of("./task.json");
    TaskList tasks;

    public TaskManager(){
        tasks = new TaskList();
        try{
            loadTaskInMemory();
        }catch (Exception exception){
            MessageDisplayer.errMessage(exception);
            exception.printStackTrace();
        }
    }


    public void loadTaskInMemory() throws Exception {
        if(!Files.exists(JSON_FILE_PATH)){
            Files.createFile(JSON_FILE_PATH);
            Files.writeString(JSON_FILE_PATH, "[\n]");
        }

        String json = Files.readString(JSON_FILE_PATH);
        //MessageDisplayer.debMessage("Contenue de fichier :: "+ json);
        parseJsonString(json);
    }

    public void parseJsonString(String json) throws Exception {
        json = json.replace("[", "").replace("]", "");
        //MessageDisplayer.debMessage("After remove [ and ] ==> "+ json);
        json = json.trim();

        String[] jsonObjects = json.split("},");

        int i = 0;
        for(String jsonObject: jsonObjects){
            jsonObject = jsonObject.replace("\n", "").trim();
            jsonObject = jsonObject.endsWith("\"") ? jsonObject+"}":jsonObject;
            jsonObjects[i++] = jsonObject;

            //MessageDisplayer.debMessage(jsonObject);
        }

        for(String jsonObject: jsonObjects){
            String[] tasksString = parseJsonObjectString(jsonObject);
            tasks.addTask(
                    new  Task(tasksString[0].split(":")[1].replace("\"", " ").trim(),
                            tasksString[1].split(":")[1].replace("\"", " ").trim(),
                            Status.getStatus(tasksString[2].split(":")[1].replace("\"", " ").trim()) ,
                            tasksString[3].split(":")[1].replace("\"", " ").trim(),
                            tasksString[4].split(":")[1].replace("\"", " ").trim()
                    )
            );
        }

    }

    public String[] parseJsonObjectString(String jsonObject){
        jsonObject = jsonObject.replace("{", " ").replace("}", " ").trim();
        //MessageDisplayer.debMessage(jsonObject);
        return jsonObject.split(", ");
    }

    /*
     * Charger la liste des taches du fichier en memoire,
     * Y ajouter celle passer en parametre
     * sauvegarder dans le fichier
     */

    public void saveTask(String description) throws IOException {
        tasks.addTask(description);
        Files.writeString(JSON_FILE_PATH, tasks.toJson());
    }

    /*
    * Tasks listing
    */
    public  void listTask(){
        tasks.list();
    }
    public  void listTaskTodo(){
        tasks.listTodo();
    }
    public  void listTaskInProgress(){
        tasks.listProgress();
    }
    public  void listTaskDone(){
        tasks.listDone();
    }
} 
