package fr.iutdeck.messages.mapping;

public class MissingParametersException extends MappingException {
    public MissingParametersException(MessageMapper<?> mapper) {
        super(mapper, "Missing parameters");
    }
}
