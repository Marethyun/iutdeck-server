package fr.iutdeck.messages;

import java.util.HashMap;

public final class JoinRoomOfflineMessage implements GameMessage {
    public static final String NAME = "join_room_offline";
    private static final String PARAMETER_ROOM_ID = "room_id";
    private static final String PARAMETER_PSEUDONYM = "pseudonym";

    public final int roomId;
    public final String pseudonym;

    public JoinRoomOfflineMessage(int roomId, String pseudonym) {
        this.roomId = roomId;
        this.pseudonym = pseudonym;
    }

    public static final class Formalizer implements MessageFormalizer<JoinRoomOfflineMessage> {
        @Override
        public FormalizedMessage formalize(JoinRoomOfflineMessage message) {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_ROOM_ID, message.roomId);
            parameters.put(PARAMETER_PSEUDONYM, message.pseudonym);

            return new FormalizedMessage(NAME, parameters);
        }
    }

    public static final class Specializer implements MessageSpecializer<JoinRoomOfflineMessage> {
        @Override
        public JoinRoomOfflineMessage specialize(FormalizedMessage message) {
            HashMap<String, Object> parameters = message.getParameters();

            int roomId = ((Number) parameters.get(PARAMETER_ROOM_ID)).intValue();

            return new JoinRoomOfflineMessage(roomId, (String) parameters.get(PARAMETER_PSEUDONYM));
        }
    }
}
