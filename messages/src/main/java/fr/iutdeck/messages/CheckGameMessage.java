package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class CheckGameMessage implements GameMessage {
    public static final String NAME = "check_game";
    private static final String PARAMETER_GAME_TOKEN = "game_token";

    public final String gameToken;

    public CheckGameMessage(String gameToken) {
        this.gameToken = gameToken;
    }

    public static final class Mapper implements MessageMapper<CheckGameMessage> {

        @Override
        public FormalizedMessage formalize(CheckGameMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_GAME_TOKEN, message.gameToken);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public CheckGameMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_GAME_TOKEN))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            return new CheckGameMessage((String) parameters.get(PARAMETER_GAME_TOKEN));
        }
    }
}
