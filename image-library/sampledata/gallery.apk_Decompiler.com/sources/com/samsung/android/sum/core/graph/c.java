package com.samsung.android.sum.core.graph;

import android.os.Bundle;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.BufferChannelDescriptor;
import com.samsung.android.sum.core.functional.BufferChannelProvider;
import com.samsung.android.sum.core.message.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Message.BundledDataHandler, BufferChannelProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f4110a;

    public /* synthetic */ c(Object obj) {
        this.f4110a = obj;
    }

    public void accept(Bundle bundle) {
        bundle.putParcelableArray("buffer-list", new MediaBuffer[]{(MediaBuffer) this.f4110a});
    }

    public BufferChannel getBufferChannel(BufferChannelDescriptor bufferChannelDescriptor) {
        return ((MFGraphUnitFactory) this.f4110a).newBufferChannel(bufferChannelDescriptor);
    }
}
