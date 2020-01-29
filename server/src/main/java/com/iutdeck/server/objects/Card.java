package com.iutdeck.server.objects;

import com.iutdeck.server.CardState;

import java.util.ArrayList;
import java.util.List;

public final class Card extends GameObject implements Castable, Modifiable<Card> {
    private int info;
    private int defense;
    private Castable action;

    private ArrayList<Modifier<Card>> modifiers = new ArrayList<>();

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
     * Caste l'action de la carte
     */
    @Override
    public boolean cast(GameObject target) {
        return this.action.cast(target);
    }

    @Override
    public void modify(Modifier<Card> modifier) {
        this.modifiers.add(modifier);
    }

    @Override
    public List<Modifier<Card>> modifiers() {
        return this.modifiers;
    }

    @Override
    public Card modified() {
        Card modified = new Card(this);
        this.modifiers.forEach(modifier -> modifier.modify(modified));
        return modified;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Castable getAction() {
        return action;
    }

    public void setAction(Castable action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Card{" +
                "info=" + info +
                ", defense=" + defense +
                ", modifiers=" + modifiers +
                '}';
    }
}
