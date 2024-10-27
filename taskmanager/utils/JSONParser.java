package taskmanager.utils;

import taskmanager.model.Status;
import taskmanager.model.Task;
import taskmanager.model.TaskRepository;

import java.nio.file.Files;
import java.nio.file.Path;

public class JSONParser {
    // Load task from file in memory
    public static TaskRepository loadTaskInMemory(Path JSON_FILE_PATH) throws Exception {
        if(!Files.exists(JSON_FILE_PATH)){
            Files.createFile(JSON_FILE_PATH);
            Files.writeString(JSON_FILE_PATH, "[\n]");
        }

        String json = Files.readString(JSON_FILE_PATH);
        return parseJsonString(json);
    }
    // retrieve data from json string retrieved
    public static TaskRepository parseJsonString(String json) throws Exception {
        // remove square braces
        json = json.replace("[", "").replace("]", "");
        json = json.trim();

        // divide by object
        String[] jsonObjects = json.split("},");

        // clean each objects and close it with }
        int i = 0;
        for(String jsonObject: jsonObjects){
            jsonObject = jsonObject.replace("\n", "").trim();
            jsonObject = jsonObject.endsWith("\"") ? jsonObject+"}":jsonObject;
            jsonObjects[i++] = jsonObject;
        }

        // initialize the task repository to be returned
        TaskRepository tasksList = new TaskRepository();
        for(String jsonObject: jsonObjects){
            getAttributesValue(jsonObject);
            String[] tasksString = getAttributesValue(jsonObject);
            tasksList.addTask(
                    new Task(
                            tasksString[0],
                            tasksString[1],
                            Status.getStatus(tasksString[2]) ,
                            tasksString[4],
                            tasksString[3]
                    )
            );
        }
        return  tasksList;
    }


    // split each attribute and get values of object
    public  static  String[] getAttributesValue(String jsonObject){
        String[] attributesAndValue = jsonObject.replace("{", " ")
                .replace("}", " ")
                .trim()
                .split(",");

        String[] tableOfValues = {"","","","",""};
        int i = 0;
        for (String attributeAndValue:attributesAndValue){
            tableOfValues[i++] = attributeAndValue.split(":")[1].replace("\"", " ").trim();
        }

        return tableOfValues;
    }
}
