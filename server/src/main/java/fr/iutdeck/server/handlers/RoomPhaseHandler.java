package fr.iutdeck.server.handlers;

import fr.iutdeck.server.Player;
import fr.iutdeck.server.Room;
import fr.iutdeck.server.Server;

public abstract class RoomPhaseHandler<I> extends ServerHandler<I> {
    private Room room;
    private Player me;
    private Player opponent;

    protected RoomPhaseHandler(Server server, Room room, Player me, Player opponent) {
        super(server);
        this.room = room;
        this.me = me;
        this.opponent = opponent;
    }

    public final Room getRoom() {
        return room;
    }

    public final Player getMe() {
        return me;
    }

    public final Player getOpponent() {
        return opponent;
    }
}
