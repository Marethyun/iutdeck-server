package fr.iutdeck.server;

import com.google.gson.Gson;
import fr.iutdeck.netutils.ByteToJsonCodec;
import fr.iutdeck.netutils.FormalizedToSpecializedCodec;
import fr.iutdeck.netutils.JsonToFormalizedCodec;
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

    private static class ServerInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            Gson gson = new Gson();

            ch.pipeline().addLast(
                    new JsonObjectDecoder(),
                    new ByteToJsonCodec(gson),
                    new JsonToFormalizedCodec(gson),
                    new FormalizedToSpecializedCodec() // TODO IMPL MESSAGES
            );
        }
    }
}
