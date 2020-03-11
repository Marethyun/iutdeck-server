package fr.iutdeck.server;

import io.netty.channel.ChannelPipeline;

public interface Phase {
    /**
     * Loads the phase using the provided pipeline
     * @param pipeline The pipeline where to load the phase
     */
    void load(ChannelPipeline pipeline);
    void unload(ChannelPipeline pipeline);
}
