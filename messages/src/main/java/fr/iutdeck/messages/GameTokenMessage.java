package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class GameTokenMessage implements GameMessage {
    public static final String NAME = "game_token";
    private static final String PARAMETER_TOKEN = "token";

    public final String token;

    public GameTokenMessage(String token) {
        this.token = token;
    }

    public static final class Mapper implements MessageMapper<GameTokenMessage> {

        @Override
        public FormalizedMessage formalize(GameTokenMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_TOKEN, message.token);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public GameTokenMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_TOKEN))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            return new GameTokenMessage((String) parameters.get(PARAMETER_TOKEN));
        }
    }
}
