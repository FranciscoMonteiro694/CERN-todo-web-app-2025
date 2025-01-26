package ch.cern.todo.exceptions;

public class InvalidLengthException extends RuntimeException {

    public InvalidLengthException(String message) {
        super(message);
    }
}
