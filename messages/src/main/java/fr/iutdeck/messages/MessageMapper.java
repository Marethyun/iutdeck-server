package fr.iutdeck.messages;

public abstract class MessageMapper<T> {
    public abstract FormalizedMessage formalize(T message) throws MappingException;
    public abstract T specialize(FormalizedMessage message) throws MappingException;

    protected final String messageName(GameMessage message) {
        MessageName annotation = message.getClass().getAnnotation(MessageName.class);
        return annotation == null ? null : annotation.value();
    }
}
