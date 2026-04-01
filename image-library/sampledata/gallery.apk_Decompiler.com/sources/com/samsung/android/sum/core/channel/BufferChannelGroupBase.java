package com.samsung.android.sum.core.channel;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BufferChannelGroupBase implements BufferChannelGroup {
    protected List<BufferChannel> channels;

    public void addBufferChannel(BufferChannel bufferChannel) {
        this.channels.add(bufferChannel);
    }
}
