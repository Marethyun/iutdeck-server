package fr.iutdeck.server.objects;

public interface Modifiable<T> {
    void modify(Modifier<T> modifier);

}
