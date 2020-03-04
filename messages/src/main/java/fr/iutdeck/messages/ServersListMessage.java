package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class ServersListMessage implements GameMessage {
    public static final String NAME = "servers_list";
    private static final String PARAMETER_USER_TOKEN = "user_token";

    public final String userToken;

    public ServersListMessage(String userToken) {
        this.userToken = userToken;
    }

    public static final class Mapper implements MessageMapper<ServersListMessage> {

        @Override
        public FormalizedMessage formalize(ServersListMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();

            parameters.put(PARAMETER_USER_TOKEN, message.userToken);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public ServersListMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_USER_TOKEN))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            return new ServersListMessage((String) parameters.get(PARAMETER_USER_TOKEN));
        }
    }
}
