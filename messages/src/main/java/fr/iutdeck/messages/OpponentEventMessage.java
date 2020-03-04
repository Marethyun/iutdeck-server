package fr.iutdeck.messages;

/**
 * Mapping mechanics may be defined somewhere else
 */
public class OpponentEventMessage implements GameMessage {
    public static final String NAME = "opponent_event";
    private static final String PARAMETER_EVENT = "event";

    public final GameMessage event;

    public OpponentEventMessage(GameMessage event) {
        this.event = event;
    }
}
