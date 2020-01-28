package com.iutdeck.server;

public abstract class GameObject {
    protected final int identifier;

    protected GameObject(int identifier) {
        this.identifier = identifier;
    }
}
