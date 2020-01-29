package com.iutdeck.server.events;

import com.iutdeck.server.objects.GameObject;
import com.iutdeck.server.IngoingEvent;
import com.iutdeck.server.OutgoingEvent;

public final class AttackEvent implements IngoingEvent, OutgoingEvent {
    public final GameObject issuer;
    public final GameObject target;

    public AttackEvent(GameObject issuer, GameObject target) {
        this.issuer = issuer;
        this.target = target;
    }
}
