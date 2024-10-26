
package taskmanager;

/**
 *
 * @author Rux-Lsr
 */
public class TaskCli {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaskManager taskManager = new  TaskManager();
        if (args.length == 0) {
            defaultOutput();
        }else{
            switch (args[0]) {
                case "add":
                    try {
                        if(args.length == 2 && !args[1].isEmpty()){
                            taskManager.add(args[1]);
                            System.out.println("task added successfully(ID: "+args[1]+")");
                        }else{
                            throw new Exception("Syntax Error: add <task to add>");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        if(args.length == 2 && !args[1].isEmpty()){
                            taskManager.delete(args[1]);
                            System.out.println("task deleted : (ID:"+args[1]+")");
                        }else{
                            throw new Exception("Syntax error: delete <task_id to delete>");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case "list":
                    System.out.printf("%-4s %-25s %-15s %-25s %-25s\n", "Id", "Description", "Status", "Created At", "Updated At");
                    try {
                        if(args.length == 1){
                            taskManager.listTask();
                        }else if(args.length == 2 && !args[1].isEmpty()){
                            switch (args[1]){
                                case "todo" -> {
                                    System.out.println("tasks marked as todo :");
                                    taskManager.listTaskTodo();
                                }
                                case "done" -> {
                                    System.out.println("tasks marked as done :");
                                    taskManager.listTaskDone();
                                }
                                case "in-progress" -> {
                                    System.out.println("tasks marked as in-progress :");
                                    taskManager.listTaskInProgress();
                                }
                            }
                        }else{
                            throw new Exception("Syntax error: list <todo|done|in-progress> default <all>");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "mark-in-progress":
                    try {
                        if(args.length == 2 && !args[1].isEmpty()){
                            taskManager.markAsInProgress(args[1]);
                            System.out.println("in-progress ... (ID:"+args[1]+")");
                        }else{
                            throw new Exception("Syntax error: mark-in-progress [task_id to mark] ");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "mark-done":
                    try {
                        if(args.length == 2 && !args[1].isEmpty()){
                            taskManager.markAsDone(args[1]);
                            System.out.println("done (ID:"+args[1]+")");
                        }else{
                            throw new Exception("Syntax error: mark-done [task_id to mark] ");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "update":
                    try {
                        if(args.length == 3 && !args[1].isEmpty() && !args[2].isEmpty()){
                            taskManager.updateDescription(args[1], args[2]);
                            System.out.println("task updated : ID-> ("+args[1]+")=> \""+ args[2]+"\"");
                        }else{
                            throw new Exception("Syntax error: update [task_id to update] [new task description]");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                default:
                    defaultOutput();
            }
        }
    }

    public static void defaultOutput(){
        System.out.println("Params: ");
        System.out.println("-> add <description of the task>");
        System.out.println("-> delete <task_id to delete>");
        System.out.println("-> update <task_id to update> <new task description>");
        System.out.println("-> mark-in-progress <task_id to mark>");
        System.out.println("-> mark-done [task_id to mark]");
        System.out.println("-> list <done|todo|in-progress|> default all");
    }
   
}
