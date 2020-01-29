package com.iutdeck.server.objects;

public enum CardType {
    ;

    public final int info;
    public final int defense;
    public final Castable action;

    CardType(int info, int defense, Castable action) {
        this.info = info;
        this.defense = defense;
        this.action = action;
    }
}
