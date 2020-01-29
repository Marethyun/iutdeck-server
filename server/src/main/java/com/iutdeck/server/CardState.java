package com.iutdeck.server;

public enum CardState {
    ATTACK(true),
    DEFENSE(false);

    public final boolean representation;

    CardState(boolean representation) {
        this.representation = representation;
    }
}
