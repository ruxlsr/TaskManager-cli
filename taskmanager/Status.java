/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package taskmanager;

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
    
    
}
