/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package taskmanager.model;

import taskmanager.MessageDisplayer;

/**
 *
 * @author Rux-Lsr
 */
public enum Status {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");
    
    private String name;
    
    Status(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Status getStatus(String name) throws Exception {
        switch (name){
            case "TODO":
                return Status.TODO;
            case "IN_PROGRESS":
                return Status.IN_PROGRESS;
            case "DONE":
                return Status.DONE;
            default:
                MessageDisplayer.errMessage(name);
                throw new Exception("Status not found");
        }
    }
    
    
}
