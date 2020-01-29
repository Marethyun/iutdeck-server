package com.iutdeck.server.objects;

public final class Card extends GameObject implements Castable {
    private CardType type;

    public Card(int identifier, CardType type) {
        super(identifier);
        this.type = type;
    }

    /**
     * Caste l'action de la carte
     */
    @Override
    public boolean cast(GameObject target) {
        return type.action.cast(target);
    }
}
