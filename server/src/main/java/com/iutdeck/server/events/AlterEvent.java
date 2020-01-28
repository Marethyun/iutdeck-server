package com.iutdeck.server.events;

import com.iutdeck.server.OutgoingEvent;

public final class AlterEvent implements OutgoingEvent {
    public final int hp;

    public AlterEvent(int hp) {
        this.hp = hp;
    }
}
