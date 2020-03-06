package fr.iutdeck.messages;

import java.util.HashMap;

public final class PokeMessage implements GameMessage {
    public static final String NAME = "poke";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_ADDRESS = "address";
    private static final String PARAMETER_PORT = "port";

    public final String serverName;
    public final String serverAddress;
    public final int serverPort;

    public PokeMessage(String serverName, String serverAddress, int serverPort) {
        this.serverName = serverName;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public static final class Formalizer implements MessageFormalizer<PokeMessage> {
        @Override
        public FormalizedMessage formalize(PokeMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_NAME, message.serverName);
            parameters.put(PARAMETER_ADDRESS, message.serverAddress);
            parameters.put(PARAMETER_PORT, message.serverPort);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<PokeMessage> {
        @Override
        public PokeMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            int port = ((Number) parameters.get(PARAMETER_PORT)).intValue();

            return new PokeMessage((String) parameters.get(PARAMETER_NAME), (String) parameters.get(PARAMETER_ADDRESS), port);
        }
    }
}
