package fr.iutdeck.messages;

import java.util.HashMap;

public final class ReadyMessage implements GameMessage {
    public static final String NAME = "ready";

    private static final String PARAMETER_READY = "ready";

    public final boolean ready;

    public ReadyMessage(boolean ready) {
        this.ready = ready;
    }

    public static final class Formalizer implements MessageFormalizer<ReadyMessage> {
        @Override
        public FormalizedMessage formalize(ReadyMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_READY, message.ready);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<ReadyMessage> {
        @Override
        public ReadyMessage specialize(FormalizedMessage message) {
            return new ReadyMessage((boolean) message.getParameters().get(PARAMETER_READY));
        }
    }
}
