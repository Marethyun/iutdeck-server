package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class UserMessage implements GameMessage {
    public static final String NAME = "user";
    private static final String PARAMETER_USER_ID = "user_id";
    private static final String PARAMETER_PSEUDONYM = "pseudonym";

    public final int userId;
    public final String pseudonym;

    public UserMessage(int userId, String pseudonym) {
        this.userId = userId;
        this.pseudonym = pseudonym;
    }

    public static final class Mapper implements MessageMapper<UserMessage> {

        @Override
        public FormalizedMessage formalize(UserMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_USER_ID, message.userId);
            parameters.put(PARAMETER_PSEUDONYM, message.pseudonym);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public UserMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_USER_ID) || !parameters.containsKey(PARAMETER_PSEUDONYM))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            int userId = ((Number) parameters.get(PARAMETER_USER_ID)).intValue();

            return new UserMessage(userId, (String) parameters.get(PARAMETER_PSEUDONYM));
        }
    }
}
