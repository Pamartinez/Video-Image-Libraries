package com.samsung.android.sum.core.channel;

import android.view.Surface;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.channel.SurfaceChannelConfig;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.BufferSupplier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SurfaceWriteChannel extends SurfaceChannel, BufferSupplier {
    void configure(Surface surface) {
        configure(new SurfaceChannelConfig.Builder().setSurface(surface).build());
    }

    void configure(Surface surface, Consumer<MediaBuffer> consumer) {
        configure(new SurfaceChannelConfig.Builder().setSurface(surface).setMetaDataHandler(consumer).build());
    }

    void configure(Surface surface, MediaFormat mediaFormat, Consumer<MediaBuffer> consumer) {
        configure(new SurfaceChannelConfig.Builder().setSurface(surface).setSurfaceFormat(mediaFormat).setMetaDataHandler(consumer).build());
    }
}
