package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.cache.DiskCache;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.channel.BufferChannelDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.AsyncFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.factory.MediaFilterFactory;
import com.samsung.android.sum.core.plugin.PluginFixture;
import com.samsung.android.sum.core.plugin.PluginStore;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MFGraphUnitFactory {
    private final MediaFilterFactory mediaFilterFactory;

    public MFGraphUnitFactory(Consumer<MediaFilterFactory.Builder> consumer) {
        MediaFilterFactory.Builder builder = new MediaFilterFactory.Builder();
        consumer.accept(builder);
        builder.addCreator(AsyncFilter.class, new w(this));
        builder.addBufferChannelSupplier(new o(1, this));
        this.mediaFilterFactory = builder.build();
    }

    public static String getFilterId(MediaFilter mediaFilter) {
        return mediaFilter.getId();
    }

    public void clear() {
        this.mediaFilterFactory.clear();
    }

    public PluginFixture<?> findPlugin(MFDescriptor mFDescriptor) {
        PluginStore.Entry entry = this.mediaFilterFactory.getPluginStore().get(mFDescriptor);
        if (entry != null) {
            return entry.getPluginFixture();
        }
        return null;
    }

    public BufferChannel newBufferChannel() {
        return newBufferChannel(new BufferChannelDescriptor(0, Integer.MAX_VALUE));
    }

    public abstract BufferChannel newBufferChannel(BufferChannelDescriptor bufferChannelDescriptor);

    public abstract DiskCache newDiskCache();

    public MediaFilter newFilter(MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        return this.mediaFilterFactory.newFilter(mFDescriptor, mediaFilter);
    }

    public GraphNode<MediaFilter> newNode(MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        return newNode(this.mediaFilterFactory.newFilter(mFDescriptor, mediaFilter));
    }

    public abstract GraphNode<MediaFilter> newNode(MediaFilter mediaFilter);

    public abstract void newOutputChannelHandler(BufferChannel bufferChannel, Consumer<MediaBuffer> consumer);

    public abstract MediaFilter parallelizeFilter(MediaFilterFactory mediaFilterFactory2, MFDescriptor mFDescriptor, MediaFilter mediaFilter);

    public BufferChannel newBufferChannel(int i2) {
        return newBufferChannel(new BufferChannelDescriptor(0, i2));
    }

    public MediaFilter newFilter(MFDescriptor mFDescriptor) {
        return this.mediaFilterFactory.newFilter(mFDescriptor);
    }

    public GraphNode<MediaFilter> newNode(MFDescriptor mFDescriptor) {
        return newNode(this.mediaFilterFactory.newFilter(mFDescriptor));
    }
}
