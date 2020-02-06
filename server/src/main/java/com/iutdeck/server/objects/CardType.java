package com.iutdeck.server.objects;

public enum CardType {
    BUFFER(null, null, new Castable[]{
            (Castable<Card, Card>) (ctx, card, target) -> target.modify(element -> element.defense -= 2),
    }),

    CRACK(10, 10, new Castable[]{
            Card.ATTACK_CARD,
            Card.ATTACK_HERO
    });

    public final Integer info;
    public final Integer defense;
    public final Castable<Card, ?>[] action;

    CardType(Integer info, Integer defense, Castable<Card, ?>[] action) {
        this.info = info;
        this.defense = defense;
        this.action = action;
    }
}
