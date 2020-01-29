package com.iutdeck.server.objects;

public enum HeroType {
    ;

    public final int hp;
    public final Castable action;

    HeroType(int hp, Castable action) {
        this.hp = hp;
        this.action = action;
    }
}
