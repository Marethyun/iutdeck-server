package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class ErrorMessage implements GameMessage {

    public static final String NAME = "error";
    private static final String PARAMETER_MESSAGE = "message";
    private static final String PARAMETER_CODE = "code";

    public final String message;
    public final short code;

    public ErrorMessage(String message, short code) {
        this.message = message;
        this.code = code;
    }

    public static class Mapper implements MessageMapper<ErrorMessage> {

        @Override
        public FormalizedMessage formalize(ErrorMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_MESSAGE, message.message);
            parameters.put(PARAMETER_CODE, message.code);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public ErrorMessage specialize(FormalizedMessage message) throws MissingParametersException {
            if (!message.getParameters().containsKey(PARAMETER_MESSAGE) || !message.getParameters().containsKey(PARAMETER_CODE))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            short errorCode = ((Number) message.getParameters().get(PARAMETER_CODE)).shortValue();

            return new ErrorMessage((String) message.getParameters().get(PARAMETER_MESSAGE), errorCode);
        }
    }
}
