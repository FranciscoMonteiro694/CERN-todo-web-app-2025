package ch.cern.todo.exceptions;

public class TaskCategoryConflictException extends RuntimeException {
    public TaskCategoryConflictException(String message) {
        super(message);
    }
}
