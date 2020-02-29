package fr.iutdeck.netutils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class ByteToJsonCodec extends ByteToMessageCodec<JsonObject> {

    public static final Charset CHARSET = StandardCharsets.UTF_8;

    private final Gson gson;

    public ByteToJsonCodec(Gson gson) {
        this.gson = gson;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        //System.out.println("in: " + getClass() + " " + in.toString(CHARSET));
        out.add(gson.fromJson(in.toString(CHARSET), JsonObject.class));

        in.readerIndex(in.readableBytes()); // mark as totally read
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, JsonObject json, ByteBuf out) {
        out.writeCharSequence(json.toString(), CHARSET);
    }
}
