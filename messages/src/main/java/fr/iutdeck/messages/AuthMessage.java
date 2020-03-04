package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class AuthMessage {
    public static final String NAME = "auth";
    private static final String PARAMETER_USERNAME = "username";
    private static final String PARAMETER_PASSWORD = "password";

    public final String username;
    public final String password;

    public AuthMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static final class Mapper implements MessageMapper<AuthMessage> {

        @Override
        public FormalizedMessage formalize(AuthMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_USERNAME, message.username);
            parameters.put(PARAMETER_PASSWORD, message.password);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public AuthMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_USERNAME) || !parameters.containsKey(PARAMETER_PASSWORD))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            return new AuthMessage((String) parameters.get(PARAMETER_USERNAME), (String) parameters.get(PARAMETER_PASSWORD));
        }
    }
}
