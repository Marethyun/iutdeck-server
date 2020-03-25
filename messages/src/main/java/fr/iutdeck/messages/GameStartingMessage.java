package fr.iutdeck.messages;

public final class GameStartingMessage implements GameMessage {
    public static final String NAME = "game_starting";

    public static final class Formalizer implements MessageFormalizer<GameStartingMessage> {
        @Override
        public FormalizedMessage formalize(GameStartingMessage message) {
            return new FormalizedMessage(NAME);
        }
    }

    public static final class Specializer implements MessageSpecializer<GameStartingMessage> {
        @Override
        public GameStartingMessage specialize(FormalizedMessage message) {
            return new GameStartingMessage();
        }
    }
}
