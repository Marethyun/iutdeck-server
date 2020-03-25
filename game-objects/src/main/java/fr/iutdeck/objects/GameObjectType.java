package fr.iutdeck.objects;

/**
 * Décrit un gameobject dans sa sérialisation uniquement
 */
public enum GameObjectType {
    ROOT("ROOT"),
    CARD("CARD"),
    HERO("HERO"),
    PLAYER("PLYR"),
    OPPONENT("OPNT"),
    BOARD("BORD"),
    DECK("DECK"),
    HAND("HAND");

    public final String name;

    GameObjectType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
