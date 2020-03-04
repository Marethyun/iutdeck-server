package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public final class JoinRoomMessage implements GameMessage {
    public static final String NAME = "join_room";
    private static final String PARAMETER_ROOM_ID = "room_id";
    private static final String PARAMETER_GAME_TOKEN = "game_token";

    public final int roomId;
    public final String gameToken;

    public JoinRoomMessage(int roomId, String gameToken) {
        this.roomId = roomId;
        this.gameToken = gameToken;
    }

    public static final class Mapper implements MessageMapper<JoinRoomMessage> {

        @Override
        public FormalizedMessage formalize(JoinRoomMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("room_id", message.roomId);
            parameters.put("game_token", message.gameToken);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public JoinRoomMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_ROOM_ID) || !parameters.containsKey(PARAMETER_GAME_TOKEN))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            int roomId = ((Number) parameters.get(PARAMETER_ROOM_ID)).intValue();

            return new JoinRoomMessage(roomId, (String) parameters.get(PARAMETER_GAME_TOKEN));
        }
    }
}
