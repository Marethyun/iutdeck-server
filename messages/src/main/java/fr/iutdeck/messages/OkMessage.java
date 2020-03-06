package fr.iutdeck.messages;

public final class OkMessage implements GameMessage {
    public static final String NAME = "ok";

    public static final class Formalizer implements MessageFormalizer<OkMessage> {
        @Override
        public FormalizedMessage formalize(OkMessage message) {
            return new FormalizedMessage(NAME, null);
        }
    }

    public static final class Specializer implements MessageSpecializer<OkMessage> {
        @Override
        public OkMessage specialize(FormalizedMessage message) {
            return new OkMessage();
        }
    }
}
