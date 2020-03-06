package fr.iutdeck.messages;

import java.util.HashMap;

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

    public static final class Formalizer implements MessageFormalizer<OpponentEventMessage> {
        @Override
        public FormalizedMessage formalize(OpponentEventMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_EVENT, null /* TODO FORMALIZE THIS */);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    @SuppressWarnings("unchecked")
    public static final class Specializer implements MessageSpecializer<OpponentEventMessage> {
        @Override
        public OpponentEventMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            HashMap<String, Object> formalizedEvent = (HashMap<String, Object>) parameters.get(PARAMETER_EVENT);

            return new OpponentEventMessage(null /* TODO SPECIALIZE IT */);
        }
    }
}
