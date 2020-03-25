package fr.iutdeck.netutils;

import com.google.gson.Gson;
import fr.iutdeck.messages.ErrorMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JsonToMessageProcessingTest {

    private static final String validJson = "{\"name\":\"error\",\"properties\": {\"message\":\"Rhello\", \"code\":27}}";

    @Test
    public void testSuccess() {
        final Gson gson = new Gson();

        ByteBuf input = Unpooled.copiedBuffer(validJson, StandardCharsets.UTF_8);

        EmbeddedChannel channel = new EmbeddedChannel(
                new JsonObjectDecoder(),
                new ByteToJsonCodec(gson),
                new JsonToFormalizedCodec(gson),
                new FormalizedToSpecializedCodec()
        );

        channel.writeInbound(input);
        channel.finish();

        assertEquals(channel.inboundMessages().size(), 1);
        assertThat(channel.readInbound(), instanceOf(ErrorMessage.class));
    }
}
