package fr.iutdeck.server.handlers;

import fr.iutdeck.server.Server;
import io.netty.channel.SimpleChannelInboundHandler;

public abstract class ServerHandler<I> extends SimpleChannelInboundHandler<I> {

    private final Server server;

    protected ServerHandler(Server server) {
        this.server = server;
    }

    protected Server getServer() {
        return server;
    }
}
