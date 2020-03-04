package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class PokeMessage implements GameMessage {
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

    public static final class Mapper implements MessageMapper<PokeMessage> {

        @Override
        public FormalizedMessage formalize(PokeMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_NAME, message.serverName);
            parameters.put(PARAMETER_ADDRESS, message.serverAddress);
            parameters.put(PARAMETER_PORT, message.serverPort);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public PokeMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_NAME) || !parameters.containsKey(PARAMETER_ADDRESS) || !parameters.containsKey(PARAMETER_PORT))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            int port = ((Number) parameters.get(PARAMETER_PORT)).intValue();

            return new PokeMessage((String) parameters.get(PARAMETER_NAME), (String) parameters.get(PARAMETER_ADDRESS), port);
        }
    }
}
