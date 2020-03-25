package fr.iutdeck.server.handlers;

import fr.iutdeck.server.Room;
import fr.iutdeck.server.Server;

public abstract class RoomPhaseHandler<I> extends ServerHandler<I> {
    private Room room;

    protected RoomPhaseHandler(Server server, Room room) {
        super(server);
    }

    public Room getRoom() {
        return room;
    }
}
