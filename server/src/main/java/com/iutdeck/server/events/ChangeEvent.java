package com.iutdeck.server.events;

import com.iutdeck.server.objects.Card;
import com.iutdeck.server.OutgoingEvent;

public final class ChangeEvent implements OutgoingEvent {
    public final Card card;
    public final int info;
    public final int def;

    public ChangeEvent(Card card, int info, int def) {
        this.card = card;
        this.info = info;
        this.def = def;
    }
}
