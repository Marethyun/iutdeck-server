package fr.iutdeck.messages.mapping;

public class NameMismatchException extends MappingException {
    public NameMismatchException(MessageMapper<?> mapper) {
        super(mapper, "Formalized name does not match");
    }
}
