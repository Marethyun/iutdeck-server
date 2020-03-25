package fr.iutdeck.messages;

public final class QuitRoomMessage implements GameMessage {
    public static final String NAME = "quit_room";

    public static final class Formalizer implements MessageFormalizer<QuitRoomMessage> {
        @Override
        public FormalizedMessage formalize(QuitRoomMessage message) {
            return new FormalizedMessage(NAME);
        }
    }

    public static final class Specializer implements MessageSpecializer<QuitRoomMessage> {
        @Override
        public QuitRoomMessage specialize(FormalizedMessage message) {
            return new QuitRoomMessage();
        }
    }
}
