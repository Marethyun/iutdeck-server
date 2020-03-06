package fr.iutdeck.messages;

import java.util.HashMap;

public final class UserMessage implements GameMessage {
    public static final String NAME = "user";
    private static final String PARAMETER_USER_ID = "user_id";
    private static final String PARAMETER_PSEUDONYM = "pseudonym";

    public final int userId;
    public final String pseudonym;

    public UserMessage(int userId, String pseudonym) {
        this.userId = userId;
        this.pseudonym = pseudonym;
    }

    public static final class Formalizer implements MessageFormalizer<UserMessage> {

        @Override
        public FormalizedMessage formalize(UserMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_USER_ID, message.userId);
            parameters.put(PARAMETER_PSEUDONYM, message.pseudonym);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<UserMessage> {

        @Override
        public UserMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            int userId = ((Number) parameters.get(PARAMETER_USER_ID)).intValue();

            return new UserMessage(userId, (String) parameters.get(PARAMETER_PSEUDONYM));
        }
    }
}
