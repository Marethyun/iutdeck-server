package fr.iutdeck.messages;

import java.util.HashMap;

public final class ServersListMessage implements GameMessage {
    public static final String NAME = "servers_list";
    private static final String PARAMETER_USER_TOKEN = "user_token";

    public final String userToken;

    public ServersListMessage(String userToken) {
        this.userToken = userToken;
    }

    public static final class Formalizer implements MessageFormalizer<ServersListMessage> {
        @Override
        public FormalizedMessage formalize(ServersListMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_USER_TOKEN, message.userToken);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<ServersListMessage> {
        @Override
        public ServersListMessage specialize(FormalizedMessage message) {
            return new ServersListMessage((String) message.getParameters().get(PARAMETER_USER_TOKEN));
        }
    }
}
