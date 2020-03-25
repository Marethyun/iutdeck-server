package fr.iutdeck.netutils;

import fr.iutdeck.messages.*;
import fr.iutdeck.netutils.exception.InvalidFormalizedException;
import fr.iutdeck.netutils.exception.NoFormalizerException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

public final class FormalizedToSpecializedCodec extends MessageToMessageCodec<FormalizedMessage, GameMessage> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, FormalizedMessage message, List<Object> list) throws InvalidFormalizedException {
        MessageSpecializer<? extends GameMessage> specializer = MessageMeta.getSpecializer(message.getName());

        if (specializer == null)
            throw new InvalidFormalizedException("Cannot retrieve specializer for the message named " + message.getName());

        list.add(specializer.specialize(message)); // TODO Check cast exceptions ?
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, GameMessage gameMessage, List<Object> list) throws NoFormalizerException {
        MessageFormalizer formalizer = MessageMeta.getFormalizer(gameMessage.getClass());

        if (formalizer == null)
            throw new NoFormalizerException("Cannot retrieve formalizer for the message with class " + gameMessage.getClass());

        list.add(formalizer.formalize(gameMessage)); // TODO Check cast exceptions ?
    }
}
