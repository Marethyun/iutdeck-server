package com.iutdeck.server.objects;

public enum CardType {
    BUFFER(0, 0, target -> {
        if (target instanceof Card) {
            ((Card) target).modify(card -> card.setDefense(card.getDefense() + 2));
            return true;
        } else return false;
    }),

    CRACK(10, 10, target -> false);

    public final int info;
    public final int defense;
    public final Castable action;

    CardType(int info, int defense, Castable action) {
        this.info = info;
        this.defense = defense;
        this.action = action;
    }
}
