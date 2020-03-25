package fr.iutdeck.server.phases;

import fr.iutdeck.netutils.FormalizedToSpecializedCodec;
import fr.iutdeck.server.Phase;
import fr.iutdeck.server.Server;
import io.netty.channel.ChannelPipeline;

public class DefaultPhase implements Phase {
    @Override
    public void load(ChannelPipeline pipeline) {
        FormalizedToSpecializedCodec flag = (FormalizedToSpecializedCodec) pipeline.get(Server.ServerInitializer.FLAG_NAME);

    }

    @Override
    public void unload(ChannelPipeline pipeline) {

    }
}
