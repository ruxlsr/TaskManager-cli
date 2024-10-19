package taskmanager;

import java.util.Arrays;

public class MessageDisplayer {
    public static void errMessage(String err_message){
        System.err.println("ERROR :: "+err_message);
    }

    public static void debMessage(String debMessage){
        System.out.println("DEBUG :: "+debMessage);
    }


    static void errMessage(Exception ex){
        System.err.println("========================EXCEPTION-MESSAGE=================================== ");
        System.err.println("EXCEPTION-CLASS :: "+ex.getClass());
        System.err.println("EXCEPTION-MESSAGE :: "+ex.getMessage());
        System.err.println("EXCEPTION-LOCALISED-MESSAGE :: "+ex.getLocalizedMessage());
        System.err.println("EXCEPTION-CAUSE :: "+ex.getCause());
        System.err.println("EXCEPTION-StackTrace :: "+ Arrays.toString(ex.getStackTrace()));
    }

}
