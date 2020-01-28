package com.iutdeck.server.events;

import com.iutdeck.server.Card;
import com.iutdeck.server.IngoingEvent;
import com.iutdeck.server.OutgoingEvent;

public final class PickEvent implements IngoingEvent, OutgoingEvent {
    public final Card card;

    public PickEvent(Card card) {
        this.card = card;
    }
}
