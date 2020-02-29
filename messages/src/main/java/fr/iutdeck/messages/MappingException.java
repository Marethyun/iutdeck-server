package fr.iutdeck.messages;

public class MappingException extends RuntimeException {
    public MappingException(MessageMapper<?> mapper, String message) {
        super(String.format("%s : %s", mapper.getClass(), message));
    }
}
