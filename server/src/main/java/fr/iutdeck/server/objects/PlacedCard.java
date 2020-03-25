package fr.iutdeck.server.objects;

import fr.iutdeck.server.CardState;

import java.util.HashMap;

public class PlacedCard extends Card {
    CardState state;

    public PlacedCard(Card other) {
        super(other);
    }

    @Override
    public HashMap<String, Object> parameters() {
        HashMap<String, Object> parameters = super.parameters();
        parameters.put("state", state.representation);
        return parameters;
    }
}
