package fr.iutdeck.messages;

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

    public static final class Formalizer implements MessageFormalizer<JoinRoomMessage> {
        @Override
        public FormalizedMessage formalize(JoinRoomMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_ROOM_ID, message.roomId);
            parameters.put(PARAMETER_GAME_TOKEN, message.gameToken);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<JoinRoomMessage> {
        @Override
        public JoinRoomMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            int roomId = ((Number) parameters.get(PARAMETER_ROOM_ID)).intValue();

            return new JoinRoomMessage(roomId, (String) parameters.get(PARAMETER_GAME_TOKEN));
        }
    }
}
