
package taskmanager.model;

import taskmanager.MessageDisplayer;

public enum Status {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Status getStatus(String name) throws Exception {
        return switch (name) {
            case "TODO" -> Status.TODO;
            case "IN_PROGRESS" -> Status.IN_PROGRESS;
            case "DONE" -> Status.DONE;
            default -> {
                MessageDisplayer.errMessage(name);
                throw new Exception("Status not found");
            }
        };
    }

}
