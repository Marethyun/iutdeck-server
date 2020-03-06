package fr.iutdeck.messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ServersMessage implements GameMessage {
    public static final String NAME = "servers";
    private static final String PARAMETER_LIST = "list";

    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_ADDRESS = "address";
    private static final String PARAMETER_PORT = "port";
    private static final String PARAMETER_ONLINE = "online";
    private static final String PARAMETER_OFFICIAL = "official";

    public final List<ServerEntry> serversList;

    public ServersMessage(List<ServerEntry> serversList) {
        this.serversList = serversList;
    }

    public static final class ServerEntry {
        public final String name;
        public final String address;
        public final int port;
        public final boolean online;
        public final boolean official;

        public ServerEntry(String name, String address, int port, boolean online, boolean official) {
            this.name = name;
            this.address = address;
            this.port = port;
            this.online = online;
            this.official = official;
        }
    }

    public static final class Formalizer implements MessageFormalizer<ServersMessage> {
        @Override
        public FormalizedMessage formalize(ServersMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            ArrayList<HashMap<String, Object>> serversList = new ArrayList<>();

            for (ServerEntry serverEntry : message.serversList) {
                HashMap<String, Object> mappedEntry = new HashMap<>();
                mappedEntry.put(PARAMETER_NAME, serverEntry.name);
                mappedEntry.put(PARAMETER_ADDRESS, serverEntry.address);
                mappedEntry.put(PARAMETER_PORT, serverEntry.port);
                mappedEntry.put(PARAMETER_ONLINE, serverEntry.online);
                mappedEntry.put(PARAMETER_OFFICIAL, serverEntry.official);
                serversList.add(mappedEntry);
            }

            parameters.put(PARAMETER_LIST, serversList);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<ServersMessage> {
        @SuppressWarnings("unchecked")
        @Override
        public ServersMessage specialize(FormalizedMessage message) {
            ArrayList<HashMap<String, Object>> mappedServerEntries = (ArrayList<HashMap<String, Object>>) message.getParameters().get(PARAMETER_LIST);
            ArrayList<ServerEntry> entries = new ArrayList<>();

            for (HashMap<String, Object> mappedServerEntry : mappedServerEntries) {
                entries.add(new ServerEntry(
                        (String) mappedServerEntry.get(PARAMETER_NAME),
                        (String) mappedServerEntry.get(PARAMETER_ADDRESS),
                        ((Number) mappedServerEntry.get(PARAMETER_PORT)).intValue(),
                        (boolean) mappedServerEntry.get(PARAMETER_ONLINE),
                        (boolean) mappedServerEntry.get(PARAMETER_OFFICIAL)
                ));
            }

            return new ServersMessage(entries);
        }
    }
}
