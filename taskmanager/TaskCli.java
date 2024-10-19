
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
                            System.out.println("task added => \""+args[1]+"\"");
                        }else{
                            throw new Exception("Syntax Error: add [task to add]");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        if(args.length == 2 && !args[1].isEmpty()){
                            taskManager.delete(args[1]);
                            System.out.println("task deleted : ID -> ("+args[1]+")");
                        }else{
                            throw new Exception("Syntax error: delete [task_id to delete]");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;

                case "list":
                    try {
                        if(args.length == 1){
                            System.out.println("your command  :: "+args[0]);
                            taskManager.listTask();
                        }else if(args.length == 2){
                            System.out.println("your command  :: "+args[0]+" "+ args[1]);
                            switch (args[1]){
                                case "todo" -> taskManager.listTaskTodo();
                                case "done" -> taskManager.listTaskDone();
                                case "in-progress" -> taskManager.listTaskInProgress();
                            }
                        }else{
                            throw new Exception("Syntax error: list");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "mark-in-progress":
                    try {
                        if(args.length == 2 && !args[1].isEmpty()){
                            System.out.println("your command :: "+args[0]+" "+ args[1]);
                            taskManager.markAsInProgress(args[1]);
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
                            System.out.println("your command  :: "+args[0]+" "+ args[1]);
                            taskManager.markAsDone(args[1]);
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
                    //defaultOutput();
            }
        }
    }
    
    public static void defaultOutput(){
        System.out.println("Params: ");
        System.out.println("-> add [task to add]");
        System.out.println("-> delete [task_id to delete]");
        System.out.println("-> update [task_id to update] [new task description]");
        System.out.println("-> mark-in-progress [task_id to mark]");
        System.out.println("-> mark-done [task_id to mark]");
        System.out.println("-> list [done|todo|in-progress|all] default all");
    }
   
}
