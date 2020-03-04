package fr.iutdeck.messages.mapping;

public class MappingException extends RuntimeException {
    public MappingException(MessageMapper<?> mapper, String message) {
        super(String.format("%s : %s", mapper.getClass(), message));
    }
}
