package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class ServerInfoMessage implements GameMessage {
    public static final String NAME = "server_info";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_ONLINE = "online";
    private static final String PARAMETER_ROOMS = "rooms";

    public final String name;
    public final boolean online;
    public final short rooms;

    public ServerInfoMessage(String name, boolean online, short rooms) {
        this.name = name;
        this.online = online;
        this.rooms = rooms;
    }

    public static final class Mapper implements MessageMapper<ServerInfoMessage> {

        @Override
        public FormalizedMessage formalize(ServerInfoMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_NAME, message.name);
            parameters.put(PARAMETER_ONLINE, message.online);
            parameters.put(PARAMETER_ROOMS, message.rooms);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public ServerInfoMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_NAME) || !parameters.containsKey(PARAMETER_ONLINE) || !parameters.containsKey(PARAMETER_ROOMS))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            short rooms = ((Number) parameters.get(PARAMETER_ROOMS)).shortValue();

            return new ServerInfoMessage((String) parameters.get(PARAMETER_NAME), (boolean) parameters.get(PARAMETER_ONLINE), rooms);
        }
    }
}
