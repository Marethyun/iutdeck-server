package fr.iutdeck.server.objects;

import fr.iutdeck.server.Castable;

public enum HeroType {
    ;

    public final int hp;
    public final Castable action;

    HeroType(int hp, Castable action) {
        this.hp = hp;
        this.action = action;
    }
}
