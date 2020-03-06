package fr.iutdeck.messages;

import java.util.HashMap;

public final class CheckGameMessage implements GameMessage {
    public static final String NAME = "check_game";
    private static final String PARAMETER_GAME_TOKEN = "game_token";

    public final String gameToken;

    public CheckGameMessage(String gameToken) {
        this.gameToken = gameToken;
    }

    public static final class Formalizer implements MessageFormalizer<CheckGameMessage> {
        @Override
        public FormalizedMessage formalize(CheckGameMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_GAME_TOKEN, message.gameToken);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<CheckGameMessage> {
        @Override
        public CheckGameMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            return new CheckGameMessage((String) parameters.get(PARAMETER_GAME_TOKEN));
        }
    }
}
