package fr.iutdeck.messages;

import java.util.HashMap;

public final class AuthMessage implements GameMessage {
    public static final String NAME = "auth";
    private static final String PARAMETER_USERNAME = "username";
    private static final String PARAMETER_PASSWORD = "password";

    public final String username;
    public final String password;

    public AuthMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static final class Formalizer implements MessageFormalizer<AuthMessage> {
        @Override
        public FormalizedMessage formalize(AuthMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_USERNAME, message.username);
            parameters.put(PARAMETER_PASSWORD, message.password);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<AuthMessage> {
        @Override
        public AuthMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            return new AuthMessage((String) parameters.get(PARAMETER_USERNAME), (String) parameters.get(PARAMETER_PASSWORD));
        }
    }
}
