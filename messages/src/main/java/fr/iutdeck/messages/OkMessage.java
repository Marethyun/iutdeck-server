package fr.iutdeck.messages;

public class OkMessage implements GameMessage {

    public static final String NAME = "ok";

    public static class Mapper extends MessageMapper<OkMessage> {
        @Override
        public FormalizedMessage formalize(OkMessage message) {
            return new FormalizedMessage(NAME,null);
        }

        @Override
        public OkMessage specialize(FormalizedMessage message) throws MappingException {
            return new OkMessage();
        }
    }
}
