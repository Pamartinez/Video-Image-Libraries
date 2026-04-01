package com.samsung.android.sum.core.filter.collection;

import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.ParallelDescriptor;
import com.samsung.android.sum.core.filter.MediaFilterGroupBase;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ParallelFilter extends MediaFilterGroupBase {
    private final ParallelDescriptor descriptor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        SHARED,
        DNC
    }

    public ParallelFilter(ParallelDescriptor parallelDescriptor, Function<Integer, BufferChannel> function) {
        this.descriptor = parallelDescriptor;
        this.channelSupplier = function;
    }

    public MFDescriptor getDescriptor() {
        return this.descriptor;
    }
}
