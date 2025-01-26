package ch.cern.todo.exceptions;

public class TaskCategoryAlreadyExistsException extends RuntimeException {
    public TaskCategoryAlreadyExistsException(String message) {
        super(message);
    }
}
