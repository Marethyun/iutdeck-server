package fr.iutdeck.server;

import com.google.gson.Gson;
import fr.iutdeck.netutils.ByteToJsonCodec;
import fr.iutdeck.netutils.FormalizedToSpecializedCodec;
import fr.iutdeck.netutils.JsonToFormalizedCodec;
import fr.iutdeck.server.phases.DefaultPhase;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.json.JsonObjectDecoder;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Server implements Runnable {

    /**
     * null if offline
     */
    private final InetSocketAddress authenticationServer;

    /**
     * null if offline ou unofficial
     **/
    public final String authenticationToken;
    public final InetAddress listeningAddress;
    public final int roomsCount;
    public final String name;
    public final Room[] rooms;

    public Server(InetSocketAddress authenticationServer, String authenticationToken, InetAddress listeningAddress, int roomsCount, String name) {

        this.authenticationServer = authenticationServer;
        this.authenticationToken = authenticationToken;
        this.listeningAddress = listeningAddress;
        this.roomsCount = roomsCount;
        this.name = name;

        this.rooms = new Room[roomsCount];
        for (int i = 0; i < this.rooms.length; i++)
            rooms[i] = new Room();
    }

    @Override
    public void run() {

    }

    public static final class ServerInitializer extends ChannelInitializer<SocketChannel> {

        /**
         * Name of the handler responsible of decoding messages to their specialized type
         */
        public static final String FLAG_NAME = "phase_flag";

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            Gson gson = new Gson();

            ch.pipeline().addLast(
                    new JsonObjectDecoder(),
                    new ByteToJsonCodec(gson),
                    new JsonToFormalizedCodec(gson)
            );

            ch.pipeline().addLast(FLAG_NAME, new FormalizedToSpecializedCodec());

            // Starting with the default phase
            new DefaultPhase().load(ch.pipeline());
        }
    }
}
