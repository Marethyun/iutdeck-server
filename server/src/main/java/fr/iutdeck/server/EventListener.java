package fr.iutdeck.server;

public interface EventListener<E> {
    void handle(GameContext ctx, E event);
}
