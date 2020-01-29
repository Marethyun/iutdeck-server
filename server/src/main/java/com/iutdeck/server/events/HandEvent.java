package com.iutdeck.server.events;

import com.iutdeck.server.objects.Card;
import com.iutdeck.server.OutgoingEvent;

public final class HandEvent implements OutgoingEvent {
    public final Card card;

    public HandEvent(Card card) {
        this.card = card;
    }
}
