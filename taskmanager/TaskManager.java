
package taskmanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rux-Lsr
 */
public class TaskManager {
    static private TaskList taskList;
    static private Path taskJsonfile;
    static private String json;
    
    public TaskManager() {
        taskList = new TaskList();
        taskJsonfile = Path.of("./task.json");
        try {
           
            if(!Files.exists(taskJsonfile)){
                Files.createFile(taskJsonfile);
                
            }else{
                json = Files.readString(taskJsonfile);
                if(!json.equals("[]"))
                    fillTheTaskList();
            }
        } catch (Exception ex) {
            System.err.println("TaskManager constructor: "+ex.getLocalizedMessage());
        }
       // System.out.println("contenu du json: "+json);
    }
    
    public static void saveTasks(String description) throws IOException{
        taskList.addTask(description);
        Files.writeString(taskJsonfile, taskList.toJson().toString());
    }

    public static List<String> parseJson() {
        List<String> brutObject = new ArrayList<>();

        json = json.trim();
        System.out.println("debug 1" + json);

        try {
            if (json.startsWith("[") && json.endsWith("]")) {
                json = json.trim().substring(1, json.length() - 1);
                System.out.println("debug 2 " + json);
                String[] jsonObjects = json.split("\\},\\{"); // Corrigé: utiliser le séparateur correct
                System.out.println("debug 3: " + jsonObjects[0]);

                for (String objet : jsonObjects) {
                    objet = objet.trim();
                    System.out.println("debug 4: " + objet);

                    // Supprimer les éléments vides
                    if (!objet.isEmpty()) {
                        if (objet.startsWith("{") && objet.endsWith("}")) {
                            objet = objet.substring(1, objet.length() - 1);
                            objet = objet.trim();
                        } else if (objet.startsWith(",") && objet.length() > 2 && objet.charAt(1) == '{') {
                            objet = objet.substring(1, objet.length() - 1);
                            objet = objet.trim();

                            if (objet.startsWith("{")) {
                                objet = objet.substring(1, objet.length() - 1);
                            }
                            objet = objet.trim();
                        } else {
                            continue;
                        }

                        brutObject.add(objet);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("parseJson error: " + e.getLocalizedMessage());
        }

        return brutObject;
    }

    public static void fillTheTaskList() {
        List<String> parsedJson = parseJson();

        try {
            parsedJson.stream().forEach((jsonTask) -> {
                System.out.println("debug 5: " + jsonTask);
                Task task = getAttributes(jsonTask); // Obtenez l'objet Task
                taskList.addTask(task.toJson()); // Utilisez toJson pour ajouter la tâche
            });
        } catch (Exception e) {
            System.err.println("fill the task list error: " + e.getLocalizedMessage());
        }
    }

    public static Task getAttributes(String objet) {
        String[] attributs = objet.split(",");
        String[] values = new String[4]; // Tableau pour stocker les valeurs

        for (int i = 0; i < attributs.length; i++) {
            String[] parts = attributs[i].split(":"); // Diviser l'attribut et sa valeur
            if (parts.length == 2) { // Vérifier que l'attribut a une valeur
                values[i] = parts[1].trim().replaceAll("[{\"}]", ""); // Supprimer les guillemets
            }
        }

        return new Task(values[0], values[1], getStatus(values[2]), values[3]);
    }
    
    public static Status getStatus(String value){
        switch(value) {
            case "TODO":
                return Status.TODO;
            case "DONE":
                return Status.DONE;
            case "IN_PROGRESS":
                return Status.IN_PROGRESS;
            default:
                return Status.TODO;
        }
    }
    
   
} 
