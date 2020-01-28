package com.iutdeck.server.events;

import com.iutdeck.server.OutgoingEvent;

public final class ErrorEvent implements OutgoingEvent {
    public final String message;

    public ErrorEvent(String message) {
        this.message = message;
    }
}
