package fr.iutdeck.messages;

public final class InfoMessage implements GameMessage {
    public static final String NAME = "info";

    public static final class Formalizer implements MessageFormalizer<InfoMessage> {
        @Override
        public FormalizedMessage formalize(InfoMessage message) {
            return new FormalizedMessage(NAME, null);
        }
    }

    public static final class Specializer implements MessageSpecializer<InfoMessage> {
        @Override
        public InfoMessage specialize(FormalizedMessage message) {
            return new InfoMessage();
        }
    }
}
