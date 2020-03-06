package fr.iutdeck.messages;

import java.util.HashMap;

public final class UserTokenMessage implements GameMessage {
    public static final String NAME = "user_token";
    private static final String PARAMETER_TOKEN = "token";

    public final String token;

    public UserTokenMessage(String userToken) {
        this.token = userToken;
    }

    public static final class Formalizer implements MessageFormalizer<UserTokenMessage> {
        @Override
        public FormalizedMessage formalize(UserTokenMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_TOKEN, message.token);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<UserTokenMessage> {
        @Override
        public UserTokenMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            return new UserTokenMessage((String) parameters.get(PARAMETER_TOKEN));
        }
    }
}
