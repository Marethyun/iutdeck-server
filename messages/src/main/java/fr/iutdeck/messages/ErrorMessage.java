package fr.iutdeck.messages;

import java.util.HashMap;

public class ErrorMessage implements GameMessage {

    private final String message;
    private final short code;

    public ErrorMessage(String message, short code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public short getCode() {
        return code;
    }

    public static class Mapper extends MessageMapper<ErrorMessage> {

        @Override
        public FormalizedMessage formalize(ErrorMessage message) {
            //if (!(message instanceof ErrorMessage)) throw new MappingException(this, "Wrong parameter");

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("message", message.message);
            parameters.put("code", message.code);

            return new FormalizedMessage("error", parameters);
        }

        @Override
        public ErrorMessage specialize(FormalizedMessage message) throws MappingException {
            if (!message.getParameters().containsKey("message") || !message.getParameters().containsKey("code")) {
                throw new MappingException(this, "Missing parameters");
            }

            String errorMessage = (String) message.getParameters().get("message");
            short errorCode = ((Number) message.getParameters().get("code")).shortValue();

            return new ErrorMessage(errorMessage, errorCode);
        }
    }
}
