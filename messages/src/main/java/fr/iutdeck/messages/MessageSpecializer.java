package fr.iutdeck.messages;

public interface MessageSpecializer<T extends GameMessage> {
    T specialize(FormalizedMessage message);
}
