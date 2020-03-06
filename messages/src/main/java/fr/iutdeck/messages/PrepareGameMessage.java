package fr.iutdeck.messages;

import java.util.HashMap;

public final class PrepareGameMessage implements GameMessage {
    public static final String NAME = "prepare_game";
    private static final String PARAMETER_USER_TOKEN = "user_token";

    public final String userToken;

    public PrepareGameMessage(String userToken) {
        this.userToken = userToken;
    }

    public static final class Formalizer implements MessageFormalizer<PrepareGameMessage> {
        @Override
        public FormalizedMessage formalize(PrepareGameMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_USER_TOKEN, message.userToken);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<PrepareGameMessage> {
        @Override
        public PrepareGameMessage specialize(FormalizedMessage message) {
            return new PrepareGameMessage((String) message.getParameters().get(PARAMETER_USER_TOKEN));
        }
    }
}
