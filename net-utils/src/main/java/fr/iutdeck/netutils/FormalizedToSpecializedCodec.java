package fr.iutdeck.netutils;

import fr.iutdeck.messages.FormalizedMessage;
import fr.iutdeck.messages.GameMessage;
import fr.iutdeck.messages.MessageMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.Arrays;
import java.util.List;

public final class FormalizedToSpecializedCodec extends MessageToMessageCodec<FormalizedMessage, GameMessage> {

    private final List<MapperRow> mappers;

    public FormalizedToSpecializedCodec(MapperRow... mappers) {
        this.mappers = Arrays.asList(mappers);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, FormalizedMessage message, List<Object> list) throws RuntimeException /* FIXME TRÈS MAUVAIS */ {
        // TODO Créer un type spécial pour ce genre d'erreurs (à traiter dans exceptionCaught du handler suivant)
        list.add(mappers.stream().filter(row -> row.messageName.equals(message.getName())).findFirst().orElseThrow(() -> new RuntimeException(String.format("Can't find a mapper to map message %s", message))).mapper.specialize(message));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, GameMessage gameMessage, List<Object> list) throws RuntimeException /* FIXME TRÈS MAUVAIS */ {
        // TODO Créer un type spécial pour ce genre d'erreurs (à traiter dans exceptionCaught du handler suivant)
        list.add(
            mappers.stream().filter(row -> row.messageClass.equals(gameMessage.getClass())).findFirst().orElseThrow(() -> new RuntimeException(String.format("Can't find a mapper to map message %s", gameMessage.getClass())))
                .mapper.formalize(gameMessage)
        );
    }

    @SuppressWarnings("rawtypes")
    public static class MapperRow {
        public final String messageName;
        public final Class<? extends GameMessage> messageClass;
        public final MessageMapper mapper;

        public MapperRow(String messageName, Class<? extends GameMessage> messageClass, MessageMapper mapper) {
            this.messageName = messageName;
            this.messageClass = messageClass;
            this.mapper = mapper;
        }
    }
}
