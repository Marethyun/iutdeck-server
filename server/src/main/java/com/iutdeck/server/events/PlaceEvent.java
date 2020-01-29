package com.iutdeck.server.events;

import com.iutdeck.server.objects.Card;
import com.iutdeck.server.IngoingEvent;
import com.iutdeck.server.OutgoingEvent;

public final class PlaceEvent implements IngoingEvent, OutgoingEvent {
    public final Card card;

    public PlaceEvent(Card card) {
        this.card = card;
    }
}
