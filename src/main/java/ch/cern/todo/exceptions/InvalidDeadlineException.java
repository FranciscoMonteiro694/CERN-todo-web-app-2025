package ch.cern.todo.exceptions;

public class InvalidDeadlineException extends RuntimeException {
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
