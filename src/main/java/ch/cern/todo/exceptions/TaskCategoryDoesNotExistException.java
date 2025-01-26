package ch.cern.todo.exceptions;

public class TaskCategoryDoesNotExistException extends RuntimeException {
    public TaskCategoryDoesNotExistException(String message) {
        super(message);
    }
}
