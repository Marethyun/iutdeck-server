package fr.iutdeck.netutils.exception;

public class NoFormalizerException extends RuntimeException {
    public NoFormalizerException(String message) {
        super(message);
    }

    public NoFormalizerException(String message, Throwable cause) {
        super(message, cause);
    }
}
