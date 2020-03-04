package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class PrepareGameMessage implements GameMessage {
    public static final String NAME = "prepare_game";
    private static final String PARAMETER_USER_TOKEN = "user_token";

    public final String userToken;

    public PrepareGameMessage(String userToken) {
        this.userToken = userToken;
    }

    public static final class Mapper implements MessageMapper<PrepareGameMessage> {

        @Override
        public FormalizedMessage formalize(PrepareGameMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_USER_TOKEN, message.userToken);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public PrepareGameMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_USER_TOKEN))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            return new PrepareGameMessage((String) parameters.get(PARAMETER_USER_TOKEN));
        }
    }
}
