package com.iutdeck.server;

public interface EventListener<E extends GameEvent> {
    void handle(GameContext ctx, E event);
}
