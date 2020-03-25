package fr.iutdeck.server.objects;

import fr.iutdeck.objects.GameObject;
import fr.iutdeck.objects.GameObjectType;
import fr.iutdeck.server.Castable;
import fr.iutdeck.server.GameContext;

import java.util.HashMap;

public class Card extends GameObject implements Castable<Card, GameObject>, Modifiable<Card> {

    public static final Castable<Card, Card> ATTACK_CARD = (ctx, card, target) -> target.info -= Math.max(card.info - card.defense, 0);
    public static final Castable<Card, Hero> ATTACK_HERO = (ctx, card, hero) -> hero.hp -= card.info;

    // package-level access (for card effects and modifiers)
    Integer info;
    Integer defense;
    @SuppressWarnings("rawtypes")
    Castable[] action;
    final CardType type;

    public Card(Card other) {
        this.info = other.info;
        this.defense = other.defense;
        this.action = other.action;
        this.type = other.type;
    }

    public Card(CardType type) {
        this.type = type;
        this.info = type.info;
        this.defense = type.defense;
        this.action = type.action;
    }

    @Override
    public HashMap<String, Object> parameters() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("card_id", this.type.identifier);
        parameters.put("defense", this.defense);
        parameters.put("info", this.info);

        return parameters;
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
