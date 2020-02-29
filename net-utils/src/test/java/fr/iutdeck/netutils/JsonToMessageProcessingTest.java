package fr.iutdeck.netutils;

import com.google.gson.Gson;
import fr.iutdeck.messages.ErrorMessage;
import fr.iutdeck.messages.OkMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class JsonToMessageProcessingTest {

    private static final String validJson = "{\"name\":\"error\",\"properties\": {\"message\":\"Rhello\", \"code\":27}}";

    private final Gson gson = new Gson();

    @Test
    public void testSuccess() {
        final Gson gson = new Gson();

        ByteBuf input = Unpooled.copiedBuffer(validJson, StandardCharsets.UTF_8);

        EmbeddedChannel channel = new EmbeddedChannel(
                new JsonObjectDecoder(),
                new ByteToJsonCodec(gson),
                new JsonToFormalizedCodec(gson),
                new FormalizedToSpecializedCodec(
                        new FormalizedToSpecializedCodec.MapperRow("ok", OkMessage.class, new OkMessage.Mapper()),
                        new FormalizedToSpecializedCodec.MapperRow("error", ErrorMessage.class, new ErrorMessage.Mapper())
                )
        );

        channel.writeInbound(input);
        channel.finish();

        //Object in = channel.readInbound()

        assertEquals(channel.inboundMessages().size(), 1);
        assertThat(channel.readInbound(), instanceOf(ErrorMessage.class));
    }
}
