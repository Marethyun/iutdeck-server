package fr.iutdeck.server;

import io.netty.channel.SimpleChannelInboundHandler;

public abstract class ServerHandler<T> extends SimpleChannelInboundHandler<T> {
    protected final Server server;

    protected ServerHandler(Server server) {
        this.server = server;
    }
}
