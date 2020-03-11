package fr.iutdeck.server;

import fr.iutdeck.server.GameContext;

public interface Castable<E, T> {
    /**
     * @param target L'objet sur lequel on cast l'élément
     */
    void cast(GameContext ctx, E element, T target);
}
