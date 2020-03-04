package fr.iutdeck.messages;

import fr.iutdeck.messages.mapping.MappingException;
import fr.iutdeck.messages.mapping.MessageMapper;
import fr.iutdeck.messages.mapping.MissingParametersException;
import fr.iutdeck.messages.mapping.NameMismatchException;

import java.util.HashMap;

public class JoinRoomOfflineMessage implements GameMessage {
    public static final String NAME = "join_room_offline";
    private static final String PARAMETER_ROOM_ID = "room_id";
    private static final String PARAMETER_PSEUDONYM = "pseudonym";

    public final int roomId;
    public final String pseudonym;

    public JoinRoomOfflineMessage(int roomId, String pseudonym) {
        this.roomId = roomId;
        this.pseudonym = pseudonym;
    }

    public static final class Mapper implements MessageMapper<JoinRoomOfflineMessage> {

        @Override
        public FormalizedMessage formalize(JoinRoomOfflineMessage message) throws MappingException {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(PARAMETER_ROOM_ID, message.roomId);
            parameters.put(PARAMETER_PSEUDONYM, message.pseudonym);

            return new FormalizedMessage(NAME, parameters);
        }

        @Override
        public JoinRoomOfflineMessage specialize(FormalizedMessage message) throws MappingException {
            HashMap<String, Object> parameters = message.getParameters();

            if (!parameters.containsKey(PARAMETER_ROOM_ID) || !parameters.containsKey(PARAMETER_PSEUDONYM))
                throw new MissingParametersException(this);

            if (!message.getName().equals(NAME))
                throw new NameMismatchException(this);

            int roomId = ((Number) parameters.get(PARAMETER_ROOM_ID)).intValue();

            return new JoinRoomOfflineMessage(roomId, (String) parameters.get(PARAMETER_PSEUDONYM));
        }
    }
}
