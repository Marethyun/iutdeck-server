package fr.iutdeck.messages;

import java.util.HashMap;

public final class ServerInfoMessage implements GameMessage {
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

    public static final class Formalizer implements MessageFormalizer<ServerInfoMessage> {
        @Override
        public FormalizedMessage formalize(ServerInfoMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_NAME, message.name);
            parameters.put(PARAMETER_ONLINE, message.online);
            parameters.put(PARAMETER_ROOMS, message.rooms);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<ServerInfoMessage> {

        @Override
        public ServerInfoMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            short rooms = ((Number) parameters.get(PARAMETER_ROOMS)).shortValue();

            return new ServerInfoMessage((String) parameters.get(PARAMETER_NAME), (boolean) parameters.get(PARAMETER_ONLINE), rooms);
        }
    }
}
