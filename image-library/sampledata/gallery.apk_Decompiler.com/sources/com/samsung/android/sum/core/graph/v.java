package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.BufferChannelDescriptor;
import com.samsung.android.sum.core.functional.BufferChannelProvider;
import com.samsung.android.sum.core.graph.MFGraph;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements BufferChannelProvider {
    public final BufferChannel getBufferChannel(BufferChannelDescriptor bufferChannelDescriptor) {
        return MFGraph.Builder.lambda$new$0(bufferChannelDescriptor);
    }
}
