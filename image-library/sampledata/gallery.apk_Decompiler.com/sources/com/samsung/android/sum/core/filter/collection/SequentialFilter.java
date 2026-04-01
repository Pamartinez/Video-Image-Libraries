package com.samsung.android.sum.core.filter.collection;

import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.SequentialDescriptor;
import com.samsung.android.sum.core.filter.MediaFilterGroupBase;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SequentialFilter extends MediaFilterGroupBase {
    protected SequentialDescriptor descriptor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Mode {
        BATCHED,
        BUFFERED
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        PICKER,
        CONVEYOR
    }

    public SequentialFilter(SequentialDescriptor sequentialDescriptor) {
        this.descriptor = sequentialDescriptor;
    }

    public SequentialDescriptor getDescriptor() {
        return this.descriptor;
    }

    public SequentialFilter(SequentialDescriptor sequentialDescriptor, Function<Integer, BufferChannel> function) {
        this.descriptor = sequentialDescriptor;
        this.channelSupplier = function;
    }
}
