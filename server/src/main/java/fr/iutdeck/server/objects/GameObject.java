package fr.iutdeck.server.objects;

public abstract class GameObject {
    protected final int identifier;

    protected GameObject(int identifier) {
        this.identifier = identifier;
    }
}
