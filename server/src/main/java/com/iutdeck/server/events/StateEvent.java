package com.iutdeck.server.events;

import com.iutdeck.server.Card;
import com.iutdeck.server.CardState;
import com.iutdeck.server.IngoingEvent;
import com.iutdeck.server.OutgoingEvent;

public final class StateEvent implements IngoingEvent, OutgoingEvent {
    public final Card card;
    public final CardState state;

    public StateEvent(Card card, CardState state) {
        this.card = card;
        this.state = state;
    }
}
