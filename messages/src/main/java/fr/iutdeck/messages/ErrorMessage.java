package fr.iutdeck.messages;

import java.util.HashMap;

public final class ErrorMessage implements GameMessage {

    public static final String NAME = "error";
    private static final String PARAMETER_MESSAGE = "message";
    private static final String PARAMETER_CODE = "code";

    public final String message;
    public final short code;

    public ErrorMessage(String message, short code) {
        this.message = message;
        this.code = code;
    }

    public static final class Formalizer implements MessageFormalizer<ErrorMessage> {
        @Override
        public FormalizedMessage formalize(ErrorMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_MESSAGE, message.message);
            parameters.put(PARAMETER_CODE, message.code);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<ErrorMessage> {
        @Override
        public ErrorMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            short errorCode = ((Number) parameters.get(PARAMETER_CODE)).shortValue();

            return new ErrorMessage((String) parameters.get(PARAMETER_MESSAGE), errorCode);
        }
    }
}
