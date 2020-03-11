package fr.iutdeck.netutils.exception;

public class InvalidFormalizedException extends RuntimeException {
    public InvalidFormalizedException(String message) {
        super(message);
    }

    public InvalidFormalizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
