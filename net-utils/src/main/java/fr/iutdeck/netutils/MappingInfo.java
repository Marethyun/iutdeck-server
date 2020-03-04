package fr.iutdeck.netutils;

import fr.iutdeck.messages.GameMessage;
import fr.iutdeck.messages.mapping.MessageMapper;

@SuppressWarnings("rawtypes")
public class MappingInfo {
    public final String messageName;
    public final Class<? extends GameMessage> messageClass;
    public final MessageMapper mapper;

    public MappingInfo(String messageName, Class<? extends GameMessage> messageClass, MessageMapper mapper) {
        this.messageName = messageName;
        this.messageClass = messageClass;
        this.mapper = mapper;
    }
}
