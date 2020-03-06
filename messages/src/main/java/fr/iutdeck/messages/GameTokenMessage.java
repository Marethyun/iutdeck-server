package fr.iutdeck.messages;

import java.util.HashMap;

public final class GameTokenMessage implements GameMessage {

    public static final String NAME = "game_token";
    private static final String PARAMETER_TOKEN = "token";

    public final String token;

    public GameTokenMessage(String token) {
        this.token = token;
    }

    public static final class Formalizer implements MessageFormalizer<GameTokenMessage> {
        @Override
        public FormalizedMessage formalize(GameTokenMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_TOKEN, message.token);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<GameTokenMessage> {
        @Override
        public GameTokenMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            return new GameTokenMessage((String) parameters.get(PARAMETER_TOKEN));
        }
    }
}
