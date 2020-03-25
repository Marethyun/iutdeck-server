package fr.iutdeck.server.objects;

import fr.iutdeck.server.Castable;

public enum CardType {
    BUFFER(0, null, null, new Castable[]{
            (Castable<Card, Card>) (ctx, card, target) -> target.modify(element -> element.defense -= 2),
    }),

    CRACK(1, 10, 10, new Castable[]{
            Card.ATTACK_CARD,
            Card.ATTACK_HERO
    });

    public final long identifier;
    public final Integer info;
    public final Integer defense;
    public final Castable<Card, ?>[] action;

    CardType(long identifier, Integer info, Integer defense, Castable<Card, ?>[] action) {
        this.identifier = identifier;
        this.info = info;
        this.defense = defense;
        this.action = action;
    }
}
