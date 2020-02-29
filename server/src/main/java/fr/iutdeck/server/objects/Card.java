package fr.iutdeck.server.objects;

import fr.iutdeck.server.GameContext;

public final class Card extends GameObject implements Castable<Card, GameObject>, Modifiable<Card> {

    public static final Castable<Card, Card> ATTACK_CARD = (ctx, card, target) -> target.info -= Math.max(card.info - card.defense, 0);
    public static final Castable<Card, Hero> ATTACK_HERO = (ctx, card, hero) -> hero.hp -= card.info;

    // package-level access (for card effects and modifiers)
    Integer info;
    Integer defense;
    @SuppressWarnings("rawtypes")
    Castable[] action;

    public Card(Card other) {
        super(other.identifier);
        this.info = other.info;
        this.defense = other.defense;
        this.action = other.action;
    }

    public Card(int identifier, CardType type) {
        super(identifier);
        this.info = type.info;
        this.defense = type.defense;
        this.action = type.action;
    }

    /**
     * Caste l'action de la carte sur un autre gameobject
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void cast(GameContext ctx, Card card, GameObject target) {
        for (Castable castable : this.action) {
            try {
                castable.cast(ctx, card, target);
            } catch (ClassCastException e) {
                /* Si on ne peut pas cast, c'est que la carte ne peut pas être activée sur l'objet donné */
                // TODO: Lancer une exception personnalisée
            }
        }
    }

    public void cast(GameContext ctx, GameObject target) {
        this.cast(ctx, this, target);
    }

    @Override
    public void modify(Modifier<Card> modifier) {
        modifier.modify(this);
    }

    @Override
    public String toString() {
        return "Card{" +
                "info=" + info +
                ", defense=" + defense +
                '}';
    }
}
