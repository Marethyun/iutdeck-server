package fr.iutdeck.netutils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.iutdeck.messages.FormalizedMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

public class JsonToFormalizedCodec extends MessageToMessageCodec<JsonObject, FormalizedMessage> {

    public static final String NAME_FIELD = "name";
    public static final String PROPERTIES_FIELD = "properties";

    private final Gson gson;

    public JsonToFormalizedCodec(Gson gson) {
        this.gson = gson;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, JsonObject msg, List<Object> out) {
        out.add(gson.fromJson(msg, FormalizedMessage.class));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, FormalizedMessage msg, List<Object> out) {
        out.add(gson.toJson(msg));
    }
}
